package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<Board> list=dao.boardListData(start);
		/*for(Board b:list)
		{
			String day=b.getRegdate();
			day=day.substring(0,day.indexOf(" "));
			b.setRegdate(day.trim());
		}*/
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("list", list);
		return map;
	}
	
	
	
	@PostMapping("/board/insert_react")
	public ResponseEntity<Map> boardInsert(@RequestBody Board board)
	{
		Map map=new HashMap();
		try
		{
			Board _board=dao.save(board);
			map.put("board", _board);
			map.put("msg", "yes");
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	/*
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
	} */
	
	@GetMapping("/board/detail_react")
	public Board boardDetail(int no)
	{
		Board vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		String day=vo.getRegdate();
		day=day.substring(0,day.indexOf(" "));
		vo.setRegdate(day.trim());
		dao.save(vo);
		vo=dao.findByNo(no);
		return vo;
	}
	
	@GetMapping("/board/update/{no}")
	public ResponseEntity<Board> boardUpdateData(@PathVariable("no") int no)
	{
		Board board=new Board();
		try
		{
			board=dao.findByNo(no);
			
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(board,HttpStatus.OK);
	}
	@PutMapping("/board/update_ok/{no}")
	//수정할때 putmapping
	public ResponseEntity<Map> boardUpdateOk(@PathVariable("no") int no,
			@RequestBody Board board)
	{
		Map map=new HashMap();
		try
		{
			Board dbBoard=dao.findByNo(no);
			if(dbBoard.getPwd().equals(board.getPwd()))
			{
				board.setNo(no);
				board.setHit(dbBoard.getHit());
				Board b=dao.save(board);
				map.put("board", b);
				map.put("msg", "yes");
			}
			else
			{
				map.put("msg", "no");
			}
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	/*@GetMapping("/board/update_react")
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
	}*/
	
	@DeleteMapping("/board/delete/{no}/{pwd}")
	public ResponseEntity<Map> boardDelete(@PathVariable("no") int no,
			@PathVariable("pwd") String pwd)
	{
		Map map=new HashMap();
		try
		{
			Board board=dao.findByNo(no);
			if(pwd.equals(board.getPwd()))
			{
				dao.delete(board);
				map.put("msg", "yes");
			}
			else
			{
				map.put("msg", "no");
			}
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}











