package com.onemap.utl.common;

import com.onemap.domain.ManagementRecord;

public class OperationUtil {
	public static final String[] ops = new String[]{"测土配方","耕地","施肥","播种","移植","病害防治","虫害防治","草害防治","灌溉","收获"};
	public static final String[] lifecycles = new String[]{"播种出苗","越冬分蘖","返青拔节","抽穗扬花","灌浆乳熟","收获"};
	
	public static double getOperationValue(String operationType){
		if(operationType == null)
			return -1;
		
		for (int i = 0; i < ops.length; i++) {
			 if(operationType.equals(ops[i])){
				 //return (i+Math.random()) *5;
				return 2.5 + 5 * i;
			 }
		}
		
		return -1;
	}
	
	public static double getLifecycleValue(String lifecycleType){
		if(lifecycleType == null)
			return 0;
		
		for (int i = 0; i < lifecycles.length; i++) {
			 if(lifecycleType.equals(lifecycles[i])){
				 //return (i+Math.random()) *5;
				 return 2.5 + 5 * i;
			 }
		}
		
		return 0;
	}
	
	
	public static String[] getOperationValues(ManagementRecord managementRecord){
		String[] result = new String[]{"1","",""};
		if(managementRecord == null)
			return result;
		String operationType = managementRecord.getOperationType();
		if(operationType == null)
			return result;
		for (int i = 0; i < ops.length; i++) {
			 if(operationType.equals(ops[i])){
				 result[0] = String.valueOf(i+1);
				 return result; 
			 }
		}
		
		return result;
	}
	public static String getLifecycleType(String lifecycleType){
		String result = "1";
		if(lifecycleType == null)
			return result;
		for (int i = 0; i < lifecycles.length; i++) {
			 if(lifecycleType.equals(lifecycles[i])){
				 return String.valueOf(i+1);
			 }
		}
		
		
		return result;
	}
	
	
	private static String polish(Object str){
		System.out.println(str);
		if(str == null)
			return "";
		
		return str.toString();
	}
}
