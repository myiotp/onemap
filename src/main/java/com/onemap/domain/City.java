/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class City  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932208508441206526L;
	private Integer seqno;
	private String id;
	private String parent;
	private String text;
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
