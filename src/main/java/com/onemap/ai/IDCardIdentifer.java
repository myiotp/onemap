package com.onemap.ai;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class IDCardIdentifer {
	//设置APPID/AK/SK
    public static final String APP_ID = "11181275";
    public static final String API_KEY = "mVILK7LQ5TBx3DG8XXn4dKsW";
    public static final String SECRET_KEY = "p78xqyYfiWvCNNGjmtDxyMVOaqK7sO4G";
	public static void main(String[] args) {
		// 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("detect_direction", "true");
	    options.put("detect_risk", "false");
	    
	    String idCardSide = "front";
	    
	    // 参数为本地图片路径
//	    String image = "/Users/xiningwang/Downloads/1.png";
//	    JSONObject res = client.idcard(image, idCardSide, options);
//	    System.out.println(res.toString(2));

//	    String image = "/Users/xiningwang/Downloads/aobama.jpeg";
//	    JSONObject res = client.idcard(image, idCardSide, options);
//	    System.out.println(res.toString(2));
	    
	    String image = "/Users/xiningwang/Downloads/idback.jpg";
	    JSONObject res = client.idcard(image, "back", options);
	    System.out.println(res.toString(2));
	    
//	    // 参数为本地图片二进制数组
//	    byte[] file = readImageFile(image);
//	    res = client.idcard(file, idCardSide, options);
//	    System.out.println(res.toString(2));

	}

}
