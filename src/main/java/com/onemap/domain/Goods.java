package com.onemap.domain;

import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.onemap.DateJsonDeserializer;
import com.onemap.DateJsonSerializer;

import lombok.Data;

@Data
public class Goods extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345955900177596917L;
	private Integer id;
	private String username;
	private String fromAreaName;
	private String fromCityName;
	private String fromProvinceName;
	private String fromAddress;
	private String toAreaName;
	private String toCityName;
	
	private String fromid;
	private double fromlng;
	private double fromlat;
	private String fromname;
	private String toid;
	private String toname;
	private double tolng;
	private double tolat;
	
	
	private String toProvinceName;
	private String toAddress;
	private String carType;
	private double carLength;
	private int status;
	private String cargoOwner;
	
	private String ownerCellphone;
	private String cargoName;
	private double cargoWeight;
	private double vehicledimension;
	private String cargotype,ownercompany,operator;
	
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date shipTimestamp;
	
	private double price;
	private String payment;
	
	private int validDays;
	private String memo;
	private String wechat;
	private String emergencyContact;
	private String emergencyCellphone;
	private String category;
	private int mileage;
	
	private boolean favoited;
	private int txId;
	@Override
	public Integer getId() {
		return id;
	}
	
//	@DateTimeFormat(pattern="yyyy-MM-dd")  
//	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
//	public Date getShipTimestamp() {  
//	    return this.shipTimestamp;  
//	}  
}
