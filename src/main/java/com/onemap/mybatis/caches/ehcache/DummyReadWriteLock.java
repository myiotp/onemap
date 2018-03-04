/**
 * 
 */
package com.onemap.mybatis.caches.ehcache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author root
 *
 */
public class DummyReadWriteLock implements ReadWriteLock {
	 private Lock lock = new DummyLock();
	 
	/**
	 * 
	 */
	public DummyReadWriteLock() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.locks.ReadWriteLock#readLock()
	 */
	@Override
	public Lock readLock() {
		// TODO Auto-generated method stub
		return lock;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.locks.ReadWriteLock#writeLock()
	 */
	@Override
	public Lock writeLock() {
		// TODO Auto-generated method stub
		return lock;
	}

	static class DummyLock implements Lock{

		@Override
		public void lock() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean tryLock() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit)
				throws InterruptedException {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void unlock() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Condition newCondition() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
