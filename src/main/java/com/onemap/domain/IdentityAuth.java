/**
 * 
 */
package com.onemap.domain;

import java.util.List;

import lombok.Data;

/**
 * @author junpingwang
 *
 */
@Data
public class IdentityAuth  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441706526L;
	private Integer id;
	private String username;
	private int authresult;
	private String comment;
	private String type;
	private int usertype;
	private String image101;
	private String image102;
	private String image103;
	private String image104;
	
	private String image201;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAuthresult() {
		return authresult;
	}
	public void setAuthresult(int authresult) {
		this.authresult = authresult;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
