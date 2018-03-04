/*
 *    Copyright 2012-2014 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.onemap.mybatis.caches.memcached;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

import org.apache.ibatis.cache.CacheException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * @author Simone Tripodi
 */
final class MemcachedClientWrapper {

    /**
     * This class log.
     */
    private static final Log LOG = LogFactory.getLog(MemcachedCache.class);

    private final MemcachedConfiguration configuration;

    private final MemcachedClient client;

    public MemcachedClientWrapper() {
        configuration = MemcachedConfigurationBuilder.getInstance().parseConfiguration();
        try {
            client = new MemcachedClient(configuration.getConnectionFactory(), configuration.getAddresses());
        } catch (IOException e) {
            String message = "Impossible to instantiate a new memecached client instance, see nested exceptions";
            LOG.error(message, e);
            throw new RuntimeException(message, e);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Running new Memcached client using " + configuration);
        }
    }

    /**
     * Converts the MyBatis object key in the proper string representation.
     * 
     * @param key the MyBatis object key.
     * @return the proper string representation.
     */
    private String toKeyString(final Object key) {
        // issue #1, key too long
        String keyString = configuration.getKeyPrefix() + StringUtils.sha1Hex(key.toString());
        if (LOG.isDebugEnabled()) {
            LOG.debug("Object key '"
                    + key
                    + "' converted in '"
                    + keyString
                    + "'");
        }
        return keyString;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getObject(Object key) {
        String keyString = toKeyString(key);
        Object ret = retrieve(keyString);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Retrived object ("
                    + keyString
                    + ", "
                    + ret
                    + ")");
        }

        return ret;
    }

    /**
     * Return the stored group in Memcached identified by the specified key.
     *
     * @param groupKey the group key.
     * @return the group if was previously stored, null otherwise.
     */
    @SuppressWarnings("unchecked")
    private Set<String> getGroup(String groupKey) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Retrieving group with id '"
                    + groupKey
                    + "'");
        }

        Object groups = null;
        try {
            groups = retrieve(groupKey);
        } catch (Exception e) {
            LOG.error("Impossible to retrieve group '"
                    + groupKey
                    + "' see nested exceptions", e);
        }

        if (groups == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Group '"
                        + groupKey
                        + "' not previously stored");
            }
            return new HashSet<String>();
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("retrieved group '"
                    + groupKey
                    + "' with values "
                    + groups);
        }
        return (Set<String>) groups;
    }

    /**
     *
     *
     * @param keyString
     * @return
     * @throws Exception
     */
    private Object retrieve(final String keyString) {
        Object retrieved = null;

        if (configuration.isUsingAsyncGet()) {
            Future<Object> future;
            if (configuration.isCompressionEnabled()) {
                future = client.asyncGet(keyString, new CompressorTranscoder());
            } else {
                future = client.asyncGet(keyString);
            }

            try {
                retrieved = future.get(configuration.getTimeout(), configuration.getTimeUnit());
            } catch (Exception e) {
                future.cancel(false);
                throw new CacheException(e);
            }
        } else {
            if (configuration.isCompressionEnabled()) {
                retrieved = client.get(keyString, new CompressorTranscoder());
            } else {
                retrieved = client.get(keyString);
            }
        }

        return retrieved;
    }

    public void putObject(Object key, Object value, String id) {
        String keyString = toKeyString(key);
        String groupKey = toKeyString(id);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Putting object ("
                    + keyString
                    + ", "
                    + value
                    + ")");
        }

        storeInMemcached(keyString, value);

        // add namespace key into memcached
        Set<String> group = getGroup(groupKey);
        group.add(keyString);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Insert/Updating object ("
                    + groupKey
                    + ", "
                    + group
                    + ")");
        }

        storeInMemcached(groupKey, group);
    }

    /**
     * Stores an object identified by a key in Memcached.
     *
     * @param keyString the object key
     * @param value the object has to be stored.
     */
    private void storeInMemcached(String keyString, Object value) {
        if (value != null
                && !Serializable.class.isAssignableFrom(value.getClass())) {
            throw new CacheException("Object of type '"
                    + value.getClass().getName()
                    + "' that's non-serializable is not supported by Memcached");
        }

        if (configuration.isCompressionEnabled()) {
            client.set(keyString, configuration.getExpiration(), value, new CompressorTranscoder());
        } else {
            client.set(keyString, configuration.getExpiration(), value);
        }
    }

    public Object removeObject(Object key) {
        String keyString = toKeyString(key);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Removing object '"
                    + keyString
                    + "'");
        }

        Object result = getObject(key);
        if (result != null) {
            client.delete(keyString);
        }
        return result;
    }

    public void removeGroup(String id) {
        String groupKey = toKeyString(id);

        Set<String> group = getGroup(groupKey);

        if (group == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No need to flush cached entries for group '"
                        + id
                        + "' because is empty");
            }
            return;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Flushing keys: " + group);
        }

        for (String key : group) {
            client.delete(key);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Flushing group: " + groupKey);
        }

        client.delete(groupKey);
    }

    @Override
    protected void finalize() throws Throwable {
        client.shutdown(configuration.getTimeout(), configuration.getTimeUnit());
        super.finalize();
    }

}
