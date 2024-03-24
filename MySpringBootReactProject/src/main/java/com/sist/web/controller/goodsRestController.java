package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins = "*")
public class goodsRestController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("/goods/list_react")
	   public Map goodsListData(int page)
	   {
		   int rowSize=12;
		   int start=(rowSize*page)-rowSize;
		   List<goods1> list=dao.goodsListData(start);
		   
		   Map map=new HashMap();
		   int count=(int)dao.count();
		   int totalpage=(int)(Math.ceil(count/12.0));
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   map.put("curpage",page);
		   map.put("totalpage",totalpage);
		   map.put("startPage",startPage);
		   map.put("endPage", endPage);
		   map.put("count", count);
		   map.put("list", list);
		   
		   return map;
	   }
	
	@GetMapping("/goods/detail_react")
	public goods1 goods_detail(int gno)
	{
		goods1 goods=dao.findByGno(gno);
		goods.setHit(goods.getHit()+1); //조회수 증가
		dao.save(goods);
		goods=dao.findByGno(gno);
		return goods;
	}
}
