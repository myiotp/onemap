/**
 * 
 */
package com.onemap.domain;

import java.sql.Date;

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
public class UserVehicle extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608608412306526L;
	private Integer id;
	private String username, licenseplate, enginenumber, registrationaddress, vehicletype, vehiclebrand, certimage;
	private double vehicledimension, vehicleweight, cargolength;

	@JsonSerialize(using = DateJsonSerializer.class)
	@JsonDeserialize(using = DateJsonDeserializer.class)
	private Date checkdeadline, insurancedeadline;

	private double gpsx, gpsy;
	private int authresult;
	private String comment;
	private String fromAreaName;
	private String fromCityName;
	private String fromProvinceName;
	private String fromAddress;
	private String fromid;
	private double fromlng;
	private double fromlat;
	private String fromname;

	public Integer getId() {
		return id;
	}

}
