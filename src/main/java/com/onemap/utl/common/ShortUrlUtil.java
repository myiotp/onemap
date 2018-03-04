package com.onemap.utl.common;

import java.security.NoSuchAlgorithmException;

public class ShortUrlUtil {
	public static String getShortUrl4Cetupeifang(int id) {
		String url = "/onemap/managementrecord/cetupeifang?id=" + id;
		return getShortUrl(url);
	}
	public static String getShortUrl(String url) {
		if (url == null)
			return null;
		try {
			byte[] md5Bytes = md5(url.getBytes());
			String hex = byteToHexString(md5Bytes);
			String[] resUrl = new String[4];
			// 得到 4组短链接字符串
			for (int i = 0; i < 4; i++) {
				// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
				String sTempSubString = hex.substring(i * 8, i * 8 + 8);
				// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,
				// 如果不用 long ，则会越界
				long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
				String outChars = "";
				// 循环获得每组6位的字符串
				for (int j = 0; j < 6; j++) {
					// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
					// (具体需要看chars数组的长度 以防下标溢出，注意起点为0)
					long index = 0x0000003D & lHexLong;
					// 把取得的字符相加
					outChars += chars[(int) index];
					// 每次循环按位右移 5 位
					lHexLong = lHexLong >> 5;
				}
				// 把字符串存入对应索引的输出数组
				resUrl[i] = outChars;
			}

			return resUrl[0];
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 要使用生成 URL 的字符
	static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };

	public static byte[] md5(byte[] input) throws NoSuchAlgorithmException {
		java.security.MessageDigest alg = java.security.MessageDigest
				.getInstance("MD5");
		alg.update(input);
		return alg.digest();
	}

	static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * 
	 * @param tmp
	 *            要转换的byte[]
	 * @return 十六进制字符串表示形式
	 */
	public static String byteToHexString(byte[] tmp) {
		String s;
		// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
			// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
			// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符串
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://www.cnblogs.com/surge/archive/2011/10/06/2199697.html";
		System.out.println(getShortUrl(url));
	}

}
