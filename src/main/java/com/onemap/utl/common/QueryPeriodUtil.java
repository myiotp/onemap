package com.onemap.utl.common;

import javax.servlet.http.HttpServletRequest;

import com.onemap.Configuration;

public class QueryPeriodUtil {
	public static String[] getPeriod(HttpServletRequest request) {
		Object obj1 = request.getSession().getAttribute("year");
		Object obj2 = request.getSession().getAttribute("period");
		Configuration cfg = Configuration.getInstance();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
//		System.out.println(obj1+":" + obj2);
		if (obj1 == null || obj2 == null) {
			Integer[] period = cfg.getPeriod();
			if (period[1] == 0) {
				sb1.append(period[0]).append("-");
				sb1.append(cfg.getPeriod1Start()).append("-01");
				sb2.append(period[0]).append("-");
				sb2.append(cfg.getPeriod1End() + 1).append("-01");
			} else {
				sb1.append(period[0]).append("-");
				sb1.append(cfg.getPeriod2Start()).append("-01");
				sb2.append(period[0] + 1).append("-");
				sb2.append(cfg.getPeriod2End() + 1).append("-01");
			}
		} else {
			int year = Integer.parseInt(obj1.toString());
			if (obj2.toString().equals("0")) {
				sb1.append(year).append("-");
				sb1.append(cfg.getPeriod1Start()).append("-01");
				sb2.append(year).append("-");
				sb2.append(cfg.getPeriod1End() + 1).append("-01");
			} else {
				sb1.append(year).append("-");
				sb1.append(cfg.getPeriod2Start()).append("-01");
				sb2.append(year + 1).append("-");
				sb2.append(cfg.getPeriod2End() + 1).append("-01");
			}
		}

//		System.out.println( sb1.toString() + ":" +  sb2.toString());
		return new String[] { sb1.toString(), sb2.toString() };
	}
}
