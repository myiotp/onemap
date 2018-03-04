package com.onemap.mybatis.caches.memcached;

import java.util.Arrays;

import net.spy.memcached.DefaultHashAlgorithm;

public class WeakHashMapTest {

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// WeakHashMap w1=new WeakHashMap();
	// //添加三个键值对，三个key都是匿名字符串，没有其他引用
	// w1 .put("语文", "良好");
	// w1 .put("数学", "及格");
	// w1 .put("英语", "中等");
	// w1 .put("java", "good");//该key是一个系统缓存的字符串对象
	// System.out.println(w1 );//
	// //通知系统进行垃圾回收
	// System.gc();
	// System.runFinalization();
	// System.out.println(w1 );//
	// }
	public static void main(String[] args) {
		int numReps = 60;
		int group = 4;
		for (int i = 0; i < numReps / group; i++) {
			byte[] digest = DefaultHashAlgorithm.computeMd5("node" + i);
			printByteArray(digest);
			System.out.println("------------" + digest.length);	
			
			for (int h = 0; h < group; h++) {
			
//				System.out.println(Integer.toBinaryString((digest[3 + h * group]& 0xFF)<<24));
//				System.out.println(Integer.toBinaryString((digest[2 + h * group]& 0xFF)<<16));
//				System.out.println(Integer.toBinaryString((digest[1 + h * 2]& 0xFF)<<8));
//				System.out.println(Integer.toBinaryString((digest[0 + h * 2]& 0xFF)));
				Long k = ((long) (digest[3 + h * group] & 0xFF) << 24)
						| ((long) (digest[2 + h * group] & 0xFF) << 16)
						| ((long) (digest[1 + h * group] & 0xFF) << 8)
						| (digest[h * group] & 0xFF);
//				Long k = ((long) (digest[1 + h * 2] & 0xFF) << 8)
//						| (digest[h * 2] & 0xFF);
				System.out.println(Long.toBinaryString(k));
				System.out.println(k);

			}
		}
	}

	public static void printByteArray(byte[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
	}
}
