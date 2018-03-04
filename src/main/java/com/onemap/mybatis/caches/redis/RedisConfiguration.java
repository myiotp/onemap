package com.onemap.mybatis.caches.redis;

import static java.lang.String.format;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;

public class RedisConfiguration {

	/**
	 * The key prefix.
	 */
	private String keyPrefix;

	/**
	 * The Redis servers
	 */
	private List<HostAndPort> addresses;

	/**
	 * The Memcached entries expiration time.
	 */
	private int expiration;

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public List<HostAndPort> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<HostAndPort> addresses) {
		this.addresses = addresses;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	@Override
	public int hashCode() {
		return hash(1, 31, addresses, expiration, keyPrefix);
	}

	/**
	 * Computes a hashCode given the input objects.
	 * 
	 * @param initialNonZeroOddNumber
	 *            a non-zero, odd number used as the initial value.
	 * @param multiplierNonZeroOddNumber
	 *            a non-zero, odd number used as the multiplier.
	 * @param objs
	 *            the objects to compute hash code.
	 * @return the computed hashCode.
	 */
	public static int hash(int initialNonZeroOddNumber,
			int multiplierNonZeroOddNumber, Object... objs) {
		int result = initialNonZeroOddNumber;
		for (Object obj : objs) {
			result = multiplierNonZeroOddNumber * result
					+ (obj != null ? obj.hashCode() : 0);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || !obj.getClass().equals(this.getClass()))
			return false;

		RedisConfiguration other = (RedisConfiguration) obj;

		return eq(this.getAddresses(), other.getAddresses())
				&& eq(this.getKeyPrefix(), other.getKeyPrefix())
				&& eq(this.getExpiration(), other.getExpiration());
	}

	/**
	 * Verifies input objects are equal.
	 * 
	 * @param o1
	 *            the first argument to compare
	 * @param o2
	 *            the second argument to compare
	 * @return true, if the input arguments are equal, false otherwise.
	 */
	private static <O> boolean eq(O o1, O o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return format(
				"RedisConfiguration [addresses=%s, expiration=%s, keyPrefix=%s]",
				addresses, expiration, keyPrefix);
	}
}
