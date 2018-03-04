package com.onemap.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.BaseModel;
import com.onemap.utl.pages.PaginatedArrayList;

public interface BaseDao<T extends BaseModel, ID extends Serializable> {
	

    /**
     * 批量添加新实体
     * 
     * @param list
     * @return
     * @throws Exception
     */
    void save(T t) throws Exception;

    /**
     * 删除实体（软册除status=2）
     * 
     * @param id
     * @throws Exception
     */
    void delete(T t) throws Exception;

    void update(T t) throws Exception;

    /**
     * 通过ID获取实体
     * 
     * @param id
     * @return
     * @throws Exception
     */
    T get(@Param("id")ID id) throws Exception;

    /**
     * <p>
     * 带分页的查询列表，与分页相关的语句需要自己编写，mybatis将不会干扰。
     * </p>
     * 
     * @param t
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    PaginatedArrayList<T> listByLimit(T t) throws Exception;

    /**
     * <p>
     * 不带分页的列表查询。
     * </p>
     * 
     * @param t
     * @return
     */
    List<T> list(T t) throws Exception;

    /**
     * 通过id列表获取实体列表
     * 
     * @param id
     * @return
     * @throws Exception
     */
    List<T> getbyIdList(@Param("ids")List<ID> list);

    /**
     * 根据条件查记录数
     * 
     * @param t
     * @return
     * @throws Exception
     */
    int count(T t) throws Exception;
    
    /**
     * 检查是否重复
     * @param t
     * @return
     */
    boolean isDuplicated(T t);
    
   
	List<T>  tongji(T t) throws Exception;
}
