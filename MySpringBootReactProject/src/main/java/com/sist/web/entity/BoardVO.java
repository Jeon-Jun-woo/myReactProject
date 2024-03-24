package com.sist.web.entity;
//*, 원하는 컬럼만 가지고올때
public interface BoardVO {
	public int getNo();
	public String getName();
	public String getSubject();
	public String getRegdate();
	public int getHit();
}
