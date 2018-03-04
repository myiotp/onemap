/**
 * 
 */
package com.onemap.mybatis.caches.redis;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

/**
 * The Redis-based Cache implementation.
 * 
 * @author
 * 
 */
public final class RedisCache implements Cache {
	private static final RedisClientWrapper CLIENT = new RedisClientWrapper();
	/**
	 * The cache id.
	 */
	private final String id;

	/**
	 * The {@link ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

	/**
	 * 
	 */
	public RedisCache(String id) {
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
	 * @see org.apache.ibatis.cache.Cache#getSize()
	 */
	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#putObject(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void putObject(Object key, Object value) {
		CLIENT.putObject(key, value, id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#getObject(java.lang.Object)
	 */
	@Override
	public Object getObject(Object key) {
		return CLIENT.getObject(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#removeObject(java.lang.Object)
	 */
	@Override
	public Object removeObject(Object key) {
		return CLIENT.removeObject(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.cache.Cache#clear()
	 */
	@Override
	public void clear() {
		CLIENT.removeGroup(id);

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

}
