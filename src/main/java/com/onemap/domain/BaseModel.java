
package com.onemap.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -665036712667731957L;
    private boolean canCreate = false;
	private boolean canRead = true;
	private boolean canUpdate = false;
	private boolean canDelete = false;
	
    private List<Clause> whereClause;
    /**
     * 排序 升 降
     */
    private String order;
    /**
     * 排序字段
     */
    private String orderBy;
    private String orderType;
    /**
     * 分页用当前页号
     */
    private Integer page = 1;
    /**
     * 分页用记录开始位置
     */
    private Integer startPos;
    /**
     * 分页用页面大小
     */
    private Integer pageSize = 10;

    /**
     * 记录创建时间
     */
    private Date createTime;
    /**
     * 记录最后一次修改时间
     */
    private Date updateTime;
    /**
     * 创建人ID
     */
    private Integer creatorID;
    /**
     * 创建人用户名
     */
    private String creatorUserName;
    /**
     * 创建人姓名
     */
    private String creatorName;
    
    private String typename;
    private Double totalamount;
    
    private String queryBeginTime;
    private String queryEndTime;
    
    public abstract Object getId();

    public Integer getStartPos() {
        return startPos;
    }

    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this));
            }
        } catch (Exception e) { // Suppress
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public Date getCreateTime() {
        return createTime;
    }
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getQueryBeginTime() {
		return queryBeginTime;
	}

	public void setQueryBeginTime(String queryBeginTime) {
		this.queryBeginTime = queryBeginTime;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public List<Clause> getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(List<Clause> whereClause) {
		this.whereClause = whereClause;
	}
	public boolean isCanCreate() {
		return canCreate;
	}
	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}
	public boolean isCanRead() {
		return canRead;
	}
	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}
	public boolean isCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
}
