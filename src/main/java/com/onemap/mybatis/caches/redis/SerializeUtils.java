package com.onemap.mybatis.caches.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {
	public static byte[] serialize(Object object) {
		if (object == null)
			return null;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oss = new ObjectOutputStream(bos);
			oss.writeObject(object);

			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes == null)
			return null;

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		try {
			ObjectInputStream ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
