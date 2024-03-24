package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.*;
import java.util.*;
public interface BoardDAO extends JpaRepository<Board, Integer>{
	@Query(value = "SELECT no,subject,name,regdate,hit "
			+"FROM board ORDER BY no DESC "
			+"LIMIT :start,10",nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") int start);
	
	public Board findByNo(int no);
}
