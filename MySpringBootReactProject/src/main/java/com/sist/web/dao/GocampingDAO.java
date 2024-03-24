package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Gocamping;

public interface GocampingDAO extends JpaRepository<Gocamping, Integer>{
	@Query(value = "SELECT * FROM gocamping "
			+"WHERE loc LIKE CONCAT('%',:loc,'%') "
			+"ORDER BY mno ASC LIMIT :start,9",nativeQuery = true)
	public List<Gocamping> campFindData(@Param("start") Integer start,@Param("loc") String loc);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM gocamping "
			+"WHERE loc LIKE CONCAT('%',:loc,'%')",nativeQuery = true)
	public int campFindTotalPage(@Param("loc") String loc);
	
	public Gocamping findByMno(int mno);
	
	
	
	
	@Query(value = "SELECT * FROM gocamping "
			+"ORDER BY mno ASC LIMIT :start,5",nativeQuery = true)
	public List<Gocamping> campListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/5.0) FROM gocamping"
				,nativeQuery = true)
	public int campListTotalPage();
}
