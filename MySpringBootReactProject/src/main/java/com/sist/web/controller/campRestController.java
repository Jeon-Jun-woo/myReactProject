package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*")
public class campRestController {
	@Autowired
	private GocampingDAO dao;
	
	@GetMapping("camp/find_totalpage_react")
	   public String find_totalpage(String loc)
	   {
		   int total=dao.campFindTotalPage(loc);
		   return String.valueOf(total);
	   }
	   @RequestMapping("/camp/find_react")
	   public Map camp_find(int page,String loc)
	   {
		   int rowSize=12;
		   int start=(rowSize*page)-rowSize;
		   List<Gocamping> list=dao.campFindData(start, loc);
		   Map map=new HashMap();
		   int totalpage=dao.campFindTotalPage(loc);
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   map.put("curpage",page);
		   map.put("totalpage",totalpage);
		   map.put("startPage",startPage);
		   map.put("endPage", endPage);
		   map.put("list", list);
		   return map;
	   }
	   @GetMapping("/camp/list_react")
	   public Map camp_list(int page)
	   {
		   System.out.println("page:"+page);
		   int rowSize=5;
		   int start=(rowSize*page)-rowSize;
		   List<Gocamping> list=dao.campListData(start);
		   Map map=new HashMap();
		   int totalpage=dao.campListTotalPage();
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   map.put("curpage",page);
		   map.put("totalpage",totalpage);
		   map.put("startPage",startPage);
		   map.put("endPage", endPage);
		   map.put("list", list);
		   
		   return map;// response.data
		   
	   }
	   @GetMapping("camp/camp_totalpage_react")
	   public String food_totalpage()
	   {
		   int total=dao.campListTotalPage();
		   return String.valueOf(total);
	   }
	   
	   @GetMapping("camp/camp_detail_react")
	   public Gocamping camp_detail(int mno)
	   {
		   Gocamping vo=dao.findByMno(mno);
		   return vo;
	   }
	   
	   
}
