/*
 *    Copyright 2014 the original author or authors.
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
package com.onemap.mybatis.caches.hazelcast;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * {@code LoggingCache} adapter for HazelcastClientCache.
 *
 * @author Ronald Ploeger
 */
public final class LoggingHazelcastClientCache extends LoggingCache {

    public LoggingHazelcastClientCache(final String id) {
        super(new HazelcastClientCache(id));
    }

}
