package com.onemap.utl.order;


public class TransactionNumber {
    /**
     * 格式化序列号
     * @param serialNumber
     * @return 4位序列号；低位数前面用0补齐
     */
    private static String format(long serialNumber){
            return String.format("%04d", serialNumber);
    }
   public static String generate(String apId,String datestr,long currentnumber){
            StringBuilder apTransId=new StringBuilder(apId);
            apTransId.append(datestr);
            apTransId.append("-");
            apTransId.append(format(currentnumber+1));
            return apTransId.toString();
    }
public static void main(String[] args) {
	System.out.println(TransactionNumber.generate("STM","20130420",2));
	System.out.println(TransactionNumber.generate("STM","20130420",33));
}
}
