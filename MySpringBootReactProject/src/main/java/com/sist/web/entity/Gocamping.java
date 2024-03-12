package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/*
 * MNO int 
TITLE text 
SUBTITLE text 
POSTER text 
LOC text 
NUM text 
ENVIR text 
CATEGORY text 
SEASON text 
OPENCLOSE text 
HOMEPAGE text 
RESERVE text 
FACILITY text 
PIC1 text 
PIC2 text 
PIC3 text 
EXPLAIN text 
HIT int
 */
@Entity
@Getter
@Setter
public class Gocamping {
	@Id
	private int mno;
	private String title,subtitle,poster,loc,num,envir,category,season;
	private String openclose,homepage,reserve,facility,pic1,pic2,pic3,explain;
	int hit;
}
