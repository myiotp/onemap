/*
 *    Copyright 2012 the original author or authors.
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
package com.onemap.mybatis.caches.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.HostAndPort;

/**
 * Setter from String to list of InetSocketAddress representation.
 *
 * @author Simone Tripodi
 */
final class InetSocketAddressListPropertySetter extends AbstractPropertySetter<List<HostAndPort>> {

    private static final List<HostAndPort> SOCKET_LIST = Arrays.asList(new HostAndPort("localhost", 6379));

    /**
     * Instantiates a String to List&lt;InetSocketAddress&gt; setter.
     */
    public InetSocketAddressListPropertySetter() {
        super("com.onemap.mybatis.caches.redis.servers",
                "addresses",
                SOCKET_LIST);
    }

    @Override
    protected List<HostAndPort> convert(String property) throws Exception {
        return getAddresses(property);
    }

    /**
     * Split a string containing whitespace or comma separated host or IP
     * addresses and port numbers of the form "host:port host2:port" or
     * "host:port, host2:port" into a List of InetSocketAddress instances suitable
     * for instantiating a MemcachedClient.
     *
     * Note that colon-delimited IPv6 is also supported. For example: ::1:11211
     */
    public static List<HostAndPort> getAddresses(String s) {
      if (s == null) {
        throw new NullPointerException("Null host list");
      }
      if (s.trim().equals("")) {
        throw new IllegalArgumentException("No hosts in list:  ``" + s + "''");
      }
      ArrayList<HostAndPort> addrs = new ArrayList<HostAndPort>();

      for (String hoststuff : s.split("(?:\\s|,)+")) {
        if (hoststuff.equals("")) {
          continue;
        }

        int finalColon = hoststuff.lastIndexOf(':');
        if (finalColon < 1) {
          throw new IllegalArgumentException("Invalid server ``" + hoststuff
              + "'' in list:  " + s);
        }
        String hostPart = hoststuff.substring(0, finalColon);
        String portNum = hoststuff.substring(finalColon + 1);

        addrs.add(new HostAndPort(hostPart, Integer.parseInt(portNum)));
      }
      assert !addrs.isEmpty() : "No addrs found";
      return addrs;
    }
}
