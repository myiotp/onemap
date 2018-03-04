package com.onemap;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

public class Configuration {
	private static Configuration instance;

	private static int period1Start;
	private static int period1End;
	private static int period2Start;
	private static int period2End;

	private static String smsUrl;
	private static String mapUrl;
	private static String image_url;
	
	private Configuration(){
		
	}

	public static Configuration getInstance() {
		if (instance == null) {
			Properties prop = new Properties();

			try {
				prop.load(Configuration.class
						.getResourceAsStream("configuration.properties"));
				period1Start = Integer.parseInt(prop
						.getProperty("period1_start"));
				period1End = Integer.parseInt(prop.getProperty("period1_end"));
				period2Start = Integer.parseInt(prop
						.getProperty("period2_start"));
				period2End = Integer.parseInt(prop.getProperty("period2_end"));
				smsUrl = prop.getProperty("sms_url");
				mapUrl = prop.getProperty("map_url");
				image_url = prop.getProperty("image_url");
				instance = new Configuration();
				instance.setPeriod1Start(period1Start);
				instance.setPeriod1End(period1End);
				instance.setPeriod2Start(period2Start);
				instance.setPeriod2End(period2End);
				instance.setSmsUrl(smsUrl);
				instance.setMapUrl(mapUrl);
				instance.setImage_url(image_url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Integer[] getPeriod() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		// System.out.println("month:"+month);
		if (month >= getPeriod1Start() && month <= getPeriod1End()) {
			return new Integer[] { year, 0 };
		}
		if (month >= getPeriod2Start()) {
			return new Integer[] { year, 1 };
		}
		if (month <= getPeriod2End()) {
			return new Integer[] { year - 1, 1 };
		}

		return new Integer[] { year, 0 };
	}

	public int getPeriod1Start() {
		return period1Start;
	}

	public void setPeriod1Start(int period1Start) {
		this.period1Start = period1Start;
	}

	public int getPeriod1End() {
		return period1End;
	}

	public void setPeriod1End(int period1End) {
		this.period1End = period1End;
	}

	public int getPeriod2Start() {
		return period2Start;
	}

	public void setPeriod2Start(int period2Start) {
		this.period2Start = period2Start;
	}

	public int getPeriod2End() {
		return period2End;
	}

	public void setPeriod2End(int period2End) {
		this.period2End = period2End;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public static String getImage_url() {
		String getenv = System.getenv("image_url");
		System.out.println("getImage_url:"+getenv + (getenv != null && getenv.trim().length() > 0));
		if(getenv != null && getenv.trim().length() > 0) {
			return getenv;
		}
		return getInstance().image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
}
