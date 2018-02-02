package com.ral.model.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Query {

	public static int DEFAULT_PAGE_SIZE = 10;
	
	private Integer pageNow = 1;
	
	private Integer pageSize = Query.DEFAULT_PAGE_SIZE;
	
	private Integer total;
	
	private Integer pageCount;
	
	private Integer offset;
	
	private Integer rows;

	private Boolean isPage = false;
	
	public Integer getPageNow() {
		if(pageNow == null || pageNow <= 0){
			pageNow = 1;
		}
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		if(pageNow == null || pageNow <= 0){
			pageNow = 1;
		}
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageCount() {
		if(total != null && pageSize !=null){
			if(total % pageSize == 0){
				pageCount = total/pageSize;
			}else{
				pageCount = total/pageSize + 1;
			}
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	@JsonIgnore
	public Integer getOffset() {
		this.offset =  (pageNow -1) *  pageSize;
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@JsonIgnore
	public Integer getRows() {
		this.rows = pageSize;
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 设置页数
	 */
	public void initPageCount(){
		int value =  this.total / this.pageSize;
		this.pageCount = this.total % this.pageSize == 0  ? value : value + 1;
	}

	public Boolean getPage() {
		return isPage;
	}

	public void setPage(Boolean page) {
		isPage = page;
	}
}
