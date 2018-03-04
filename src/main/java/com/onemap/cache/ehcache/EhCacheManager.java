/**
 * 
 */
package com.onemap.cache.ehcache;

import java.io.IOException;
import java.io.InputStream;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author root
 *
 */
public class EhCacheManager implements CacheManager, Initializable, Destroyable {
	private static final Logger log = LoggerFactory
			.getLogger(EhCacheManager.class);
	protected net.sf.ehcache.CacheManager cacheManager;

	/**
	 * Indicates if the CacheManager instance was implicitly/automatically
	 * created by this instance, indicating that it should be automatically
	 * cleaned up as well on shutdown.
	 */
	private boolean cacheManagerImplicitlyCreated = false;

	private String cacheManagerConfigFile = "classpath:org/apache/shiro/cache/ehcache/ehcache.xml";

	public EhCacheManager() {

	}

	/**
     * Initializes this instance.
     * <p/>
     * If a {@link #setCacheManager CacheManager} has been
     * explicitly set (e.g. via Dependency Injection or programatically) prior to calling this
     * method, this method does nothing.
     * <p/>
     * However, if no {@code CacheManager} has been set, the default Ehcache singleton will be initialized, where
     * Ehcache will look for an {@code ehcache.xml} file at the root of the classpath.  If one is not found,
     * Ehcache will use its own failsafe configuration file.
     * <p/>
     * Because Shiro cannot use the failsafe defaults (fail-safe expunges cached objects after 2 minutes,
     * something not desirable for Shiro sessions), this class manages an internal default configuration for
     * this case.
     *
     * @throws org.apache.shiro.cache.CacheException
     *          if there are any CacheExceptions thrown by EhCache.
     * @see net.sf.ehcache.CacheManager#create
     */
    public final void init() throws CacheException {
        ensureCacheManager();
    }

    private net.sf.ehcache.CacheManager ensureCacheManager() {
        try {
            if (this.cacheManager == null) {
                if (log.isDebugEnabled()) {
                    log.debug("cacheManager property not set.  Constructing CacheManager instance... ");
                }
                //using the CacheManager constructor, the resulting instance is _not_ a VM singleton
                //(as would be the case by calling CacheManager.getInstance().  We do not use the getInstance here
                //because we need to know if we need to destroy the CacheManager instance - using the static call,
                //we don't know which component is responsible for shutting it down.  By using a single EhCacheManager,
                //it will always know to shut down the instance if it was responsible for creating it.
                this.cacheManager = new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream());
                if (log.isTraceEnabled()) {
                    log.trace("instantiated Ehcache CacheManager instance.");
                }
                cacheManagerImplicitlyCreated = true;
                if (log.isDebugEnabled()) {
                    log.debug("implicit cacheManager created successfully.");
                }
            }
            return this.cacheManager;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    /**
     * Shuts-down the wrapped Ehcache CacheManager <b>only if implicitly created</b>.
     * <p/>
     * If another component injected
     * a non-null CacheManager into this instace before calling {@link #init() init}, this instance expects that same
     * component to also destroy the CacheManager instance, and it will not attempt to do so.
     */
    public void destroy() {
        if (cacheManagerImplicitlyCreated) {
            try {
                net.sf.ehcache.CacheManager cacheMgr = getCacheManager();
                cacheMgr.shutdown();
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Unable to cleanly shutdown implicitly created CacheManager instance.  " +
                            "Ignoring (shutting down)...");
                }
            }
            cacheManagerImplicitlyCreated = false;
        }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * Loads an existing EhCache from the cache manager, or starts a new cache
	 * if one is not found.
	 * 
	 * @param name the name of the cache to load/create.
	 */
	@Override
	public final <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (log.isTraceEnabled()) {
			log.trace("Acquiring EhCache instance named [" + name + "]");
		}

		try {
			net.sf.ehcache.Ehcache cache = ensureCacheManager()
					.getEhcache(name);
			if (cache == null) {
				if (log.isInfoEnabled()) {
					log.info(
							"Cache with name '{}' does not yet exist.  Creating now.",
							name);
				}
				this.cacheManager.addCache(name);

				cache = this.cacheManager.getCache(name);

				if (log.isInfoEnabled()) {
					log.info("Added EhCache named [" + name + "]");
				}
			} else {
				if (log.isInfoEnabled()) {
					log.info("Using existing EHCache named [" + cache.getName()
							+ "]");
				}
			}
			return new EhCache<K, V>(cache);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	public net.sf.ehcache.CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(net.sf.ehcache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public boolean isCacheManagerImplicitlyCreated() {
		return cacheManagerImplicitlyCreated;
	}

	public void setCacheManagerImplicitlyCreated(
			boolean cacheManagerImplicitlyCreated) {
		this.cacheManagerImplicitlyCreated = cacheManagerImplicitlyCreated;
	}

	public String getCacheManagerConfigFile() {
		return cacheManagerConfigFile;
	}

	public void setCacheManagerConfigFile(String cacheManagerConfigFile) {
		this.cacheManagerConfigFile = cacheManagerConfigFile;
	}

	/**
	 * Acquires the InputStream for the ehcache configuration file using
	 * {@link ResourceUtils#getInputStreamForPath(String)
	 * ResourceUtils.getInputStreamForPath} with the path returned from
	 * {@link #getCacheManagerConfigFile() getCacheManagerConfigFile()}.
	 *
	 * @return the InputStream for the ehcache configuration file.
	 */
	protected InputStream getCacheManagerConfigFileInputStream() {
		String configFile = getCacheManagerConfigFile();
		try {
			return ResourceUtils.getInputStreamForPath(configFile);
		} catch (IOException e) {
			throw new ConfigurationException(
					"Unable to obtain input stream for cacheManagerConfigFile ["
							+ configFile + "]", e);
		}
	}
}
