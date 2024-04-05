package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
public class MainController {
	@Autowired
	private GoodsDAO gDao;
	
	@Autowired
	private GocampingDAO cDao;
	
	
	@GetMapping("/goods/main")
	public goods1 goods_main()
	{
		goods1 goods=gDao.findByGno(100);
		return goods;
	}
}
