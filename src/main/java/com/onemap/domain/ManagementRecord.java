/**
 * 
 */
package com.onemap.domain;

import java.sql.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.onemap.DateJsonDeserializer;
import com.onemap.DateJsonSerializer;

import lombok.Data;

/**
 * @author junpingwang
 *
 */
@Data
public class ManagementRecord  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String ordernumber;
	private Integer truckId;
	private Truck truckObj;
	private Goods cargoObj;
	private Integer cargoId;
	private Integer type;
	private int status;
	private String blockNumber;
	private String cropType;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date operationTime;
	
	private String operationType;
	private String operator;
	private String mgtrecordmemo;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date vardate1;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date vardate2;
	
	private String var1;
	private String var2;
	private String var3;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String item5;
	private double price1, price2, price3, price4, price5;
	private String approver1,approver2,approver3,approver4,approver5,approver6;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date datetime1,datetime2,datetime3,datetime4,datetime5,datetime6;
	
	private String company1,company2,company3,company4;
	private int approvelevel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCompany1() {
		if(StringUtils.isEmpty(company1)) {
			return "0633-7778888 7778666 6663666 6881777";
		}
		return company1;
	}
	public String getCompany2() {
		if(StringUtils.isEmpty(company2)) {
			return "0636-6233788";
		}
		return company2;
	}
	public String getCompany3() {
		if(StringUtils.isEmpty(company3)) {
			return "13181178889 15166335908 15166330088";
		}
		return company3;
	}
	public String getCompany4() {
		if(StringUtils.isEmpty(company4)) {
			return "山东省日照市莒县山东路北段东侧山东凯达物流B区";
		}
		return company4;
	}
}
