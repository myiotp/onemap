/**
 * 
 */
package com.onemap.mybatis.caches.ehcache;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * @author root
 *
 */
public class LoggingEhcache extends LoggingCache {

	public LoggingEhcache(final String id) {
		super(new EhcacheCache(id));
	}
}
