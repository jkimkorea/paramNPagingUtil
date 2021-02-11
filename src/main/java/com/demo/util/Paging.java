package com.demo.util;

public class Paging {

	private int totalCount;
	private int nowPage;
	private int pagePerRow;
	private int pagePerBlock;
	private int startRow;
	private int startPage;
	private int lastPage;
	private int prevBlock;
	private int nextBlock;
	private int endPage;
	
	public Paging() {
		this(1);
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public Paging(int nowPage) {
		this.nowPage = nowPage;
		this.pagePerRow = 5;
		this.pagePerBlock = 5;
		
		setPaging(nowPage,pagePerRow,pagePerBlock);
	}
	
	public Paging(int nowPage,int pagePerRow, int pagePerBlock) {
		this.nowPage = nowPage;
		this.pagePerRow = pagePerRow;
		this.pagePerBlock = pagePerBlock;
		
		setPaging(nowPage,pagePerRow,pagePerBlock);
	}
	
	public Paging(int nowPage, int totalCount) {
		this.nowPage = nowPage;
		this.pagePerRow = 5;
		this.pagePerBlock = 5;
		
		setPaging(nowPage, pagePerRow, pagePerBlock);
		setTotalCount(totalCount);
	}
	
	public Paging(int nowPage, int pagePerRow, int pagePerBlock, int totalCount) {
		this.nowPage = nowPage;
		this.pagePerRow = pagePerRow;
		this.pagePerBlock = pagePerBlock;
		
		setPaging(nowPage, pagePerRow, pagePerBlock);
		setTotalCount(totalCount);
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.lastPage = (int)Math.ceil((double)totalCount/pagePerRow);
		this.endPage = startPage + pagePerBlock -1;
		this.nextBlock = endPage +1;
		if(endPage >= lastPage) {
			endPage = lastPage;
			nextBlock = 0;
		}
		
	}
	
	private void setPaging(int nowPage, int pagePerRow, int pagePerBlock) {
		
		startRow =((nowPage)*pagePerRow) - pagePerRow;
		startPage = ((int)Math.ceil((double)nowPage/pagePerBlock)*pagePerBlock) - pagePerBlock + 1;
		
		if(startPage > 0) {
			prevBlock = startPage -1;
			//nextBlock = startPage + pagePerBlock +1;
		}
	}
	
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		setPaging(nowPage,pagePerRow,pagePerBlock);
	}
	
	public void setPagePerRow(int pagePerRow) {
		this.pagePerRow = pagePerRow;
		setPaging(nowPage,pagePerRow,pagePerBlock);
	}
	
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
		setPaging(nowPage, pagePerRow, pagePerBlock);
	}
	
	public void setBlock(int pagePerRow, int pagePerBlock) {
		this.pagePerRow = pagePerRow;
		this.pagePerBlock = pagePerBlock;
		setPaging(nowPage,pagePerRow, pagePerBlock);
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getPagePerRow() {
		return pagePerRow;
	}
	
	
	
	
}
