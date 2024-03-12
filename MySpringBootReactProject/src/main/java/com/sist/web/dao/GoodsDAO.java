package com.sist.web.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.*;
public interface GoodsDAO extends JpaRepository<goods1, Integer>{
	@Query(value = "SELECT * FROM goods1 "
			+"ORDER BY gno DESC LIMIT :start,12",nativeQuery = true)
	public List<goods1> goodsListData(@Param("start") int start);
}
