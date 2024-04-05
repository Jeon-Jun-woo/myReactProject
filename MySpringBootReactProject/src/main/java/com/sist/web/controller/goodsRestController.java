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
		   int rowSize=6;
		   int start=(rowSize*page)-rowSize;
		   List<goods1> list=dao.goodsListData(start);
		   
		   Map map=new HashMap();
		   int count=(int)dao.count();

		   map.put("curpage",page);
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
