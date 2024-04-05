package com.sist.web.controller;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
	@Autowired
	private MemeberDAO mDao;
	
	@GetMapping("/member/login/{id}/{pwd}")
	public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,
			@PathVariable("pwd") String pwd)
	{
		Map map=new HashMap();
		try
		{
			// map => id ,name , msg =>onSuccess / onError (HttpStatus에서받은걸 나타냄)
			int count=mDao.idCount(id);
			if(count==0)
			{
				map.put("msg", "NOID");
			}
			else
			{
				Member mem=mDao.findById(id);
				if(pwd.equals(mem.getPwd()))
				{
					map.put("name", mem.getName());
					map.put("id", mem.getId());
					map.put("msg", "OK");
				}
				//저장해도 리액트에서 알지못하기때문에
				else
				{
					map.put("msg", "NOPWD");
				}
			}
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
