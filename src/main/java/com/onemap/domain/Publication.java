package com.onemap.domain;

import java.util.Date;

public class Publication extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889733233653399676L;
	private Integer id;
	private String title,author,source,category,content,summary;	
	private Date time;
	private Integer showamount,readamount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String from) {
		this.source = from;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getShowamount() {
		return showamount;
	}
	public void setShowamount(Integer show) {
		this.showamount = show;
	}
	public Integer getReadamount() {
		return readamount;
	}
	public void setReadamount(Integer read) {
		this.readamount = read;
	}

	

}
