package com.angels.module.codegroup;

import java.sql.Date;
import java.time.LocalDateTime;

public class CodeGroupDto {
	
	private String cgSeq;
	private Integer cgUse;
	private Integer cgdelNy;
	private String cgCode;
	private String cgName;
	private String cgNameEng;
	private String cgCount;
	private String cgOrder;
	private LocalDateTime cgRegDate;
	private LocalDateTime cgEdDate;
	private String cgCheck;
	private String cgTotal;
	private String cdSeqCount;
	
	//----------------
	
	public String getCgSeq() {
		return cgSeq;
	}
	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
	}

	public Integer getCgdelNy() {
		return cgdelNy;
	}
	public void setCgdelNy(Integer cgdelNy) {
		this.cgdelNy = cgdelNy;
	}
	public String getCgCode() {
		return cgCode;
	}
	public void setCgCode(String cgCode) {
		this.cgCode = cgCode;
	}
	public String getCgName() {
		return cgName;
	}
	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	public String getCgCount() {
		return cgCount;
	}
	public void setCgCount(String cgCount) {
		this.cgCount = cgCount;
	}
	public String getCgOrder() {
		return cgOrder;
	}
	public void setCgOrder(String cgOrder) {
		this.cgOrder = cgOrder;
	}
	
	public String getCgCheck() {
		return cgCheck;
	}
	public void setCgCheck(String cgCheck) {
		this.cgCheck = cgCheck;
	}
	public String getCgTotal() {
		return cgTotal;
	}
	public void setCgTotal(String cgTotal) {
		this.cgTotal = cgTotal;
	}
	public Integer getCgUse() {
		return cgUse;
	}
	public void setCgUse(Integer cgUse) {
		this.cgUse = cgUse;
	}
	public LocalDateTime getCgRegDate() {
		return cgRegDate;
	}
	public void setCgRegDate(LocalDateTime cgRegDate) {
		this.cgRegDate = cgRegDate;
	}
	public LocalDateTime getCgEdDate() {
		return cgEdDate;
	}
	public void setCgEdDate(LocalDateTime cgEdDate) {
		this.cgEdDate = cgEdDate;
	}
	public String getCgNameEng() {
		return cgNameEng;
	}
	public void setCgNameEng(String cgNameEng) {
		this.cgNameEng = cgNameEng;
	}
	public String getCdSeqCount() {
		return cdSeqCount;
	}
	public void setCdSeqCount(String cdSeqCount) {
		this.cdSeqCount = cdSeqCount;
	}

	


	
	
	
}
