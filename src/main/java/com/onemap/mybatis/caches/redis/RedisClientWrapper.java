package com.onemap.mybatis.caches.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.cache.CacheException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.onemap.mybatis.caches.memcached.StringUtils;

final class RedisClientWrapper {

	private static final Log LOG = LogFactory.getLog(RedisCache.class);

	private final RedisConfiguration configuration;

	// private final JedisCluster cluster;

	private final Jedis jedis;

	public RedisClientWrapper() {
		configuration = RedisConfigurationBuilder.getInstance()
				.parseConfiguration();
		List<HostAndPort> clusterNodes = configuration.getAddresses();
		Set<HostAndPort> nodes = new HashSet<HostAndPort>(clusterNodes);
		HostAndPort hostAndPort = nodes.iterator().next();
		int port = hostAndPort.getPort();
		String host = hostAndPort.getHost();

		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
		jedis = jedisPool.getResource();

		// cluster = new JedisCluster(nodes);
		try {
			String ping = jedis.ping();
			if (ping == null) {
				System.err.println("####################################");
				System.err.println("请确认redis server是否已经启动.......");
				System.err.println("####################################");
			} else {
				System.out.println("####################################");
				System.out.println("redis server 已经启动.......");
				System.out.println(jedis.info());
				if (LOG.isDebugEnabled()) {
					LOG.debug("Object ping '" + ping + "'");
				}
				System.out.println("####################################");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("####################################");
			System.err.println("请确认redis server是否已经启动.......");
			System.err.println("####################################");
			System.exit(0);
		}
	}

	/**
	 * Converts the MyBatis object key in the proper string representation.
	 * 
	 * @param key
	 *            the MyBatis object key.
	 * @return the proper string representation.
	 */
	private String toKeyString(final Object key) {
		// issue #1, key too long
		String keyString = configuration.getKeyPrefix()
				+ StringUtils.sha1Hex(key.toString());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Object key '" + key + "' converted in '" + keyString
					+ "'");
		}
		return keyString;
	}

	/**
	 * Return the stored group in Redis identified by the specified key.
	 * 
	 * @param groupKey
	 *            the group key.
	 * @return the group if was previously stored, null otherwise.
	 */
	@SuppressWarnings("unchecked")
	private List<String> getGroup(String groupKey) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Retrieving group with id '" + groupKey + "'");
		}

		Object groups = null;
		try {
			groups = retrieve(groupKey);
		} catch (Exception e) {
			LOG.error("Impossible to retrieve group '" + groupKey
					+ "' see nested exceptions", e);
		}

		if (groups == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Group '" + groupKey + "' not previously stored");
			}
			return new ArrayList<String>();
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("retrieved group '" + groupKey + "' with values "
					+ groups);
		}
		
		return (ArrayList<String>) groups;
	}

	/**
	 * 
	 * 
	 * @param keyString
	 * @return
	 * @throws Exception
	 */
	private Object retrieve(final String keyString) {
		if (keyString == null)
			return null;

		byte[] bytes = jedis.get(keyString.getBytes());
		Object obj = null;
		if (bytes != null)
			obj = SerializeUtils.unserialize(bytes);
		return obj;
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
			LOG.debug("Retrived object (" + keyString + ", " + ret + ")");
		}

		return ret;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param id
	 */
	public void putObject(Object key, Object value, String groupId) {
		String keyString = toKeyString(key);
		String groupKey = toKeyString(groupId);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting object (" + keyString + ", " + value + ")");
		}

		storeInMemory(keyString, value);

		// add namespace key into redis
		List<String> group = getGroup(groupKey);
		group.add(keyString);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Insert/Updating object (" + groupKey + ", " + group
					+ ")");
		}

		storeInMemory(groupKey, group);
	}

	/**
	 * Stores an object identified by a key in Redis.
	 * 
	 * @param keyString
	 *            the object key
	 * @param value
	 *            the object has to be stored.
	 */
	private void storeInMemory(String keyString, Object value) {
		if (value != null
				&& !Serializable.class.isAssignableFrom(value.getClass())) {
			throw new CacheException("Object of type '"
					+ value.getClass().getName()
					+ "' that's non-serializable is not supported by Redis");
		}

		byte[] bytes = SerializeUtils.serialize(value);
		if (bytes != null)
			jedis.set(keyString.getBytes(), bytes);
	}

	/**
	 * Return the type of the value stored at key in form of a string. The type
	 * can be one of "none", "string", "list", "set". "none" is returned if the
	 * key does not exist. Time complexity: O(1)
	 * 
	 * @param key
	 * @return Status code reply, specifically: "none" if the key does not exist
	 *         "string" if the key contains a String value; "list" if the key
	 *         contains a List value; "set" if the key contains a Set value;
	 *         "zset" if the key contains a Sorted Set value; "hash" if the key
	 *         contains a Hash value;
	 */
	public String type(final String key) {
		return jedis.type(key);
	}

	public Object removeObject(Object key) {
		String keyString = toKeyString(key);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Removing object '" + keyString + "'");
		}

		Object result = getObject(keyString);
		if (result != null) {
			jedis.del(keyString.getBytes());
		}
		return result;
	}

	public void removeGroup(String id) {
		String groupKey = toKeyString(id);

		List<String> group = getGroup(groupKey);

		if (group == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("No need to flush cached entries for group '" + id
						+ "' because is empty");
			}
			return;
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Flushing keys: " + group);
		}

		for (String key : group) {
			jedis.del(key.getBytes());
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Flushing group: " + groupKey);
		}

		jedis.del(groupKey.getBytes());
	}

	@Override
	protected void finalize() throws Throwable {
		jedis.flushDB();
		super.finalize();
	}
}
