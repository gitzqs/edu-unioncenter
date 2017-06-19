package com.zqs.dao.basics;

import com.zqs.model.basics.REntity;

public interface IBaseMapper<T extends REntity> {
	
	/**
	 * 新增
	 * 
	 * @param entity
	 * @return int
	 */
	int save (T entity);
	
	/**
	 * 更新
	 * 
	 * @param 
	 * @return int
	 */
	int update(T entity);
}
