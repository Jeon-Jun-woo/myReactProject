package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list_react")
	public Map boardListData(int page)
	{
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*page)-rowSize;
		List<BoardVO> list=dao.boardListData(start);
		
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("list", list);
		return map;
	}
	
	
	//리덕스용
	@GetMapping("/board/total_react")
	public String board_total()
	{
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		return String.valueOf(totalpage);
	}
	
	@PostMapping("/board/insert_react")
	public String boardInsert(Board vo)
	{
		String result="";
		try
		{
			dao.save(vo); //insert
			result="yes";
		}catch(Exception ex)
		{
			result="no";
		}
		return result;
	}
	
	@GetMapping("/board/detail_react")
	public Board boardDetail(int no)
	{
		Board vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByNo(no);
		return vo;
	}
	
	@GetMapping("/board/update_react")
	public Board boardUpdate(int no)
	{
		Board vo=dao.findByNo(no);
		return vo;
	}
	
	@PostMapping("/board/update_ok_react")
	public String boardUpdateOk(Board vo)
	{
		Board dbVO=dao.findByNo(vo.getNo());
		String result="";
		if(vo.getPwd().equals(dbVO.getPwd()))
		{
			result="yes";
			vo.setHit(dbVO.getHit()); //hit를 첨부안하면 다 0값으로바뀜 (entity에서 업데이트true해줘서)
			dao.save(vo);
		}
		else
		{
			result="no";
		}
		
		return result;
	}
	@PostMapping("/board/delete_react")
	public String boardDelete(int no,String pwd)
	{
		String result="";
		Board vo=dao.findByNo(no);
		if(vo.getPwd().equals(pwd))
		{
			result="yes";
			dao.delete(vo);
		}
		else
		{
			result="no";
		}
		return result;
	}
}











