package com.angels.module.code;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CodeDto {
	
	private String cdSeq;
	private Integer cdDelNy;
	private Integer cdUse;
	private String cdName;
	private String cdNameEng;
	private String cdOrder;
	private LocalDateTime cdRegDate;
	private LocalDateTime cdEdDate;
	private Integer cdCheck;
	private String codeGroup_cgSeq;
	private String cgName;
	private String cgSeq;
	
	
	public String getCdSeq() {
		return cdSeq;
	}
	public void setCdSeq(String cdSeq) {
		this.cdSeq = cdSeq;
	}
	public Integer getCdDelNy() {
		return cdDelNy;
	}
	public void setCdDelNy(Integer cdDelNy) {
		this.cdDelNy = cdDelNy;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public String getCdNameEng() {
		return cdNameEng;
	}
	public void setCdNameEng(String cdNameEng) {
		this.cdNameEng = cdNameEng;
	}
	public String getCdOrder() {
		return cdOrder;
	}
	public void setCdOrder(String cdOrder) {
		this.cdOrder = cdOrder;
	}
	public LocalDateTime getCdRegDate() {
		return cdRegDate;
	}
	public void setCdRegDate(LocalDateTime cdRegDate) {
		this.cdRegDate = cdRegDate;
	}
	public LocalDateTime getCdEdDate() {
		return cdEdDate;
	}
	public void setCdEdDate(LocalDateTime cdEdDate) {
		this.cdEdDate = cdEdDate;
	}
	public Integer getCdCheck() {
		return cdCheck;
	}
	public void setCdCheck(Integer cdCheck) {
		this.cdCheck = cdCheck;
	}
	public String getCodeGroup_cgSeq() {
		return codeGroup_cgSeq;
	}
	public void setCodeGroup_cgSeq(String codeGroup_cgSeq) {
		this.codeGroup_cgSeq = codeGroup_cgSeq;
	}
	public Integer getCdUse() {
		return cdUse;
	}
	public void setCdUse(Integer cdUse) {
		this.cdUse = cdUse;
	}
	public String getCgName() {
		return cgName;
	}
	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	public String getCgSeq() {
		return cgSeq;
	}
	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
	}

//	for cache
	public static List<CodeDto> cachedCodeArrayList = new ArrayList<CodeDto>();


	public static List<CodeDto> getCachedCodeArrayList() {
		return cachedCodeArrayList;
	}
	public static void setCachedCodeArrayList(List<CodeDto> cachedCodeArrayList) {
		CodeDto.cachedCodeArrayList = cachedCodeArrayList;
	}
	
	
	
	
}
