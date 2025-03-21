package com.angels.module.codegroup;

public class CodeGroupVo {
	
//	paging
	private int thisPage = 1;									// 현재 페이지
	private int rowNumToShow = 5;								// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = 5;								// 화면에 보여줄 페이징 번호 갯수

	private int totalRows;										// 전체 데이터 갯수
	private int totalPages;										// 전체 페이지 번호
	private int startPage;										// 시작 페이지 번호
	private int endPage;										// 마지막 페이지 번호

	private int startRnumForMysql = 0;							// 쿼리 시작 row

//	-----
	public void setParamsPaging(int totalRows) {
//		setThisPage(1);

		setTotalRows(totalRows); // 총 데이터 갯수

		if (getTotalRows() == 0) { // 페이지 개수 설정
			setTotalPages(1); // 하나로 설정
		} else {
			setTotalPages(getTotalRows() / getRowNumToShow()); // 총 데이터 개수 / 한 페이지에 보여주는 데이터 개수
		}

		if (getTotalRows() % getRowNumToShow() > 0) { // 총 데이터가 한 페이지의 개수가 맞아 떨어지지 않다면
			setTotalPages(getTotalPages() + 1); // 페이지의 갯수를 하나 증가시켜라
		}

		if (getTotalPages() < getThisPage()) { // 현재 페이지가 총 페이지보다 클 경우
			setThisPage(getTotalPages()); // 현재 페이지를 마지막 페이지로 설정해라
		}
		
		setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1); //  시작하는 페이지 설정

		setEndPage(getStartPage() + getPageNumToShow() - 1); // 마지막 페이지 설정

		if (getEndPage() > getTotalPages()) { // 마지막 페이지 번호가 토탈페이지보다 크다면
			setEndPage(getTotalPages()); // 토탈페이지를 마지막 페이지로 설정해라
		}
		
		
		if (thisPage == 1) { // 현재 페이지가 1과 같다면
			setStartRnumForMysql(0); // 쿼리 시작 row값은 0
		} else {
			setStartRnumForMysql((getRowNumToShow() * (getThisPage()-1))); // 화면에 보여줄 데이터 줄 개수 * 지금 현재페이지 - 1
		}
		
		System.out.println("getThisPage():" + getThisPage());
		System.out.println("getTotalRows():" + getTotalRows());
		System.out.println("getRowNumToShow():" + getRowNumToShow());
		System.out.println("getTotalPages():" + getTotalPages());
		System.out.println("getStartPage():" + getStartPage());
		System.out.println("getEndPage():" + getEndPage());		
		System.out.println("getStartRnumForMysql(): " + getStartRnumForMysql());
		
	}

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public int getRowNumToShow() {
		return rowNumToShow;
	}

	public void setRowNumToShow(int rowNumToShow) {
		this.rowNumToShow = rowNumToShow;
	}

	public int getPageNumToShow() {
		return pageNumToShow;
	}

	public void setPageNumToShow(int pageNumToShow) {
		this.pageNumToShow = pageNumToShow;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartRnumForMysql() {
		return startRnumForMysql;
	}

	public void setStartRnumForMysql(int startRnumForMysql) {
		this.startRnumForMysql = startRnumForMysql;
	}

	


	

}
