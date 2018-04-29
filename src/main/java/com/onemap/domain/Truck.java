package com.onemap.domain;

import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.onemap.DateJsonDeserializer;
import com.onemap.DateJsonSerializer;

import lombok.Data;

@Data
public class Truck extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6988950211599365582L;
	private Integer id;
	private String fromAreaName;
	private String fromCityName;
	private String fromProvinceName;
	private String fromAddress;
	private String toAreaName;
	private String toCityName;
	private String toProvinceName;
	private String toAddress;
	private String carType;
	private double carLength;
	private int status;
	private String username;
	private String licenseplate;
	private String owner;
	private String ownerCellphone;
	private String fromid;
	private String fromname;
	private String toid;
	private String toname;
	
	private double fromlng;
	private double fromlat;
	private double tolng;
	private double tolat;
	
	private String truckBarnd;
	private String truckName;
	private double truckWeight;
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
	
	private String registerAreaName;
	private String registerCityName;
	private String registerProvinceName;
	
	private boolean favoited;
	
	private int txId;
	private int isinternal;
	@Override
	public Integer getId() {
		return id;
	}

}
