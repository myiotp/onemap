package com.onemap.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutOrderRecommendInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6412301469605880398L;

	private List<OutOrderRecommendInfoItem> outOrderRecommendInfoItem = new ArrayList<OutOrderRecommendInfoItem>();
	private String recommendInfo;

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addItem(OutOrderRecommendInfoItem item) {
		if (item != null)
			this.outOrderRecommendInfoItem.add(item);
	}

	public List<OutOrderRecommendInfoItem> getOutOrderRecommendInfoItem() {
		return outOrderRecommendInfoItem;
	}

	public void setOutOrderRecommendInfoItem(
			List<OutOrderRecommendInfoItem> outOrderRecommendInfoItem) {
		this.outOrderRecommendInfoItem = outOrderRecommendInfoItem;
	}

	public String getRecommendInfo() {
		
		if (this.outOrderRecommendInfoItem != null) {
			StringBuffer sb = new StringBuffer();
			for (Iterator<OutOrderRecommendInfoItem> iterator = this.outOrderRecommendInfoItem
					.iterator(); iterator.hasNext();) {
				OutOrderRecommendInfoItem item = iterator.next();
				sb.append(item.getArea().getId()).append(":")
						.append(item.getAmount());
				if(iterator.hasNext())
					sb.append(",");
			}
			recommendInfo = sb.toString();
		}
		return recommendInfo;
	}

	public void setRecommendInfo(String recommendInfo) {
		this.recommendInfo = recommendInfo;
	}

}
