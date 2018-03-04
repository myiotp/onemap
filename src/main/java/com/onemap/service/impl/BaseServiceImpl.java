package com.onemap.service.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;

import com.onemap.dao.BaseDao;
import com.onemap.domain.BaseModel;
import com.onemap.service.BaseService;
import com.onemap.shiro.GradRealm;
import com.onemap.utl.pages.PaginatedArrayList;
import com.onemap.utl.pages.PaginatedList;

public abstract class BaseServiceImpl<T extends BaseModel, ID extends Serializable> implements BaseService<T, ID> {

	public abstract BaseDao<T, ID> getDao();
	 public void save(T t) throws Exception {

	        if (t == null) {
	            return;
	        }

	        this.getDao().save(t);
	    }

	 
	    public void delete(T t) throws Exception {

	        if (t == null) {
	            return;
	        }

	        this.getDao().delete(t);
	    }

	 
	    public void update(T t) throws Exception {

	        if (t == null) {
	            return;
	        }


	        this.getDao().update(t);

	    }

	    public T get(ID id) throws Exception {

	        if (id == null) {
	            return null;
	        }
	        T t = this.getDao().get(id);
	        this.fillDetail(t);
	        return t;
	    }

	    /**
	     * 
	     */
	    public PaginatedArrayList<T> listByLimit(T t, HttpServletRequest request) throws Exception {
	    	PaginatedArrayList<T>  result = listByLimit(t);
	    	
	    	if(result.size() > 0){
	    		for (Iterator<T> iterator = result.iterator(); iterator.hasNext();) {
					T t2 = (T) iterator.next();					
					if(SecurityUtils.getSubject().hasRole(GradRealm.ADMIN)){
						t2.setCanCreate(true);
						t2.setCanDelete(true);
						t2.setCanUpdate(true);
					}
				}
	    	}
	    	return result;
	    }
	    /**
	     * <p>
	     * 带分页的列表查询。
	     * </p>
	     * 
	     * @param t
	     * @param offset
	     * @param limit
	     * @return
	     * @throws Exception
	     */
	    public PaginatedArrayList<T> listByLimit(T t) throws Exception {

	        if (t == null) {
	            return new PaginatedArrayList<T>(0, 0, 0);
	        }

	        // 查询数据库中记录的总数
	        int total = this.getDao().count(t);

	        // 构造带有分页信息的List
	        PaginatedArrayList<T> resultList = new PaginatedArrayList<T>(total, t.getPage(), t.getPageSize());
	        t.setStartPos(resultList.getStartPos());

	        List<T> queryResultList = this.getDao().listByLimit(t);
//	        System.out.println("queryResultList---------------");
//	        System.out.println(queryResultList);
	        resultList.addAll(queryResultList);

	        return resultList;
	    }

	    public PaginatedList<T> listDetailByLimit(T t) throws Exception {
	        PaginatedList<T> resultList = this.listByLimit(t);
	        for (T item : resultList) {
	            this.fillDetail(item);
	        }
	        return resultList;
	    }

	    /**
	     * <p>
	     * 不带分页的列表查询。
	     * </p>
	     * 
	     * @param t
	     * @return
	     * @throws Exception 
	     */
	    public List<T> list(T t) throws Exception {
	        List<T> resultList = this.getDao().list(t);
	        for (T item : resultList) {
	            this.fillDetail(item);
	        }
	        return resultList;
	    }

	    /**
	     * 根据条件查记录数
	     * 
	     * @param t
	     * @return
	     * @throws Exception
	     */
	    public int count(T t) throws Exception {
	        return this.getDao().count(t);
	    }

	    public boolean isDuplicated(T t) {
	        return this.getDao().isDuplicated(t);
	    }

	    public List<T>  tongji(T t) throws Exception {
	    	return this.getDao().tongji(t);
	    }
	   
	    /**
	     * 填充引用信息，抽象类中默认不做任何操作，如需填充引用信息，在子类中覆盖此方法
	     * 
	     * @param t
	     * @throws Exception 
	     */
	    protected void fillDetail(T t) throws Exception {
	    }
	

}
