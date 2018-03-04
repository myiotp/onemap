/**
 * 
 */
package com.onemap.mybatis.caches.ehcache;

import java.util.concurrent.locks.ReadWriteLock;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.ibatis.cache.Cache;

import com.onemap.cache.ehcache.EhCacheManager;

/**
 * @author root
 *
 */
public final class EhcacheCache implements Cache {
	/**
	 * The cache manager reference.
	 */
	private static final CacheManager CACHE_MANAGER = CacheManager.create();
	
	/**
	 * The cache id (namespace)
	 */
	private final String id;
	/**
	 * The cache instance
	 */
	private final Ehcache cache;
	/**
	 * Dummy lock that actually does not lock. As of 3.2.6 MyBatis does not call
	 * locking methods.
	 */
	private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

	/**
	 * 
	 */
	public EhcacheCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		if (!CACHE_MANAGER.cacheExists(id)) {
			CACHE_MANAGER.addCache(id);
		}
		this.cache = CACHE_MANAGER.getCache(id);
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#putObject(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void putObject(Object key, Object value) {
		Element element = new Element(key, value);
		this.cache.put(element);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#getObject(java.lang.Object)
	 */
	@Override
	public Object getObject(Object key) {
		Element element = this.cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#removeObject(java.lang.Object)
	 */
	@Override
	public Object removeObject(Object key) {
		Element element = this.cache.get(key);
		if (element == null)
			return null;

		this.cache.remove(key);
		return element.getObjectValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#clear()
	 */
	@Override
	public void clear() {
		this.cache.removeAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#getSize()
	 */
	@Override
	public int getSize() {
		return this.cache.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#getReadWriteLock()
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof EhcacheCache))
			return false;
		
		EhcacheCache c = (EhcacheCache)obj;
		return id.equals(c.getId());
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EHCache {" + id + "}";
	}

	// DYNAMIC PROPERTIES
	/**
	 * Sets the time to idle for an element before it expires. Is only used if
	 * the element is not eternal.
	 *
	 * @param timeToIdleSeconds
	 *            the default amount of time to live for an element from its
	 *            last accessed or modified date
	 */
	public void setTimeToIdleSeconds(long timeToIdleSeconds) {
		cache.getCacheConfiguration().setTimeToIdleSeconds(timeToIdleSeconds);
	}

	/**
	 * Sets the time to idle for an element before it expires. Is only used if
	 * the element is not eternal.
	 *
	 * @param timeToLiveSeconds
	 *            the default amount of time to live for an element from its
	 *            creation date
	 */
	public void setTimeToLiveSeconds(long timeToLiveSeconds) {
		cache.getCacheConfiguration().setTimeToLiveSeconds(timeToLiveSeconds);
	}

	/**
	 * Sets the maximum objects to be held in memory (0 = no limit).
	 *
	 * @param maxElementsInMemory
	 *            The maximum number of elements in memory, before they are
	 *            evicted (0 == no limit)
	 */
	public void setMaxEntriesLocalHeap(long maxEntriesLocalHeap) {
		cache.getCacheConfiguration().setMaxEntriesLocalHeap(
				maxEntriesLocalHeap);
	}

	/**
	 * Sets the maximum number elements on Disk. 0 means unlimited.
	 *
	 * @param maxElementsOnDisk
	 *            the maximum number of Elements to allow on the disk. 0 means
	 *            unlimited.
	 */
	public void setMaxEntriesLocalDisk(long maxEntriesLocalDisk) {
		cache.getCacheConfiguration().setMaxEntriesLocalDisk(
				maxEntriesLocalDisk);
	}

	/**
	 * Sets the eviction policy. An invalid argument will set it to null.
	 *
	 * @param memoryStoreEvictionPolicy
	 *            a String representation of the policy. One of "LRU", "LFU" or
	 *            "FIFO".
	 */
	public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
		cache.getCacheConfiguration().setMemoryStoreEvictionPolicy(
				memoryStoreEvictionPolicy);
	}
}
