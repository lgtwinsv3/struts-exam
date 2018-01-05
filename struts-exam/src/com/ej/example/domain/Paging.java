package com.ej.example.domain;

import java.util.List;

public class Paging<T> {

    private Object body;
    private int rowCount; // 화면에 보여질 게시물 수.
    private int pageCount; // 화면에 보여질 페이지 수.
    private int page; // 현재 페이지 번호
    private long allCount; // 총 모델 갯수
    private int endRowNum; // 현재 페이지에서 마지막 모델번호
    private int startPage; // 시작 페이지
    private int endPage; // 끝 페이지
    private int firstPage; // 현 블럭에서 첫 페이지
    private int prevPage; // 이전 페이지
    private int nextPage; // 다음 페이지
    private int lastPage; // 현재 블럭에서 마지막 페이지

    private int skipRowCount;
    private Object searchCondition;
    private String searchKeyword;

    public Paging() {
        this(1, 15);
        this.pageCount = 10;
    }

    public Paging(int pageNo, int size) {
        this.body = new Object();
        this.page = pageNo;
//        this.allCount = count;
        this.rowCount = size;
        this.pageCount = 10;
    }

    public Paging<T> calcPaging(int totalRecordCount) {
        int totalPage = 0;
        int allCount = totalRecordCount;
        int nowPage = this.getPage();

        int startRowNum = 0;
        int endRowNum = 0;

        // 페이지 계산에 따른 블럭 설정.
        int totalBlock = 0;
        int nowBlock = 0;

        int firstPage = 0;
        int prevPage = 0;
        int nextPage = 0;
        int lastPage = 0;

        int startPage = 0;
        int endPage = 0;

        int skipRowCount = 0;
        //

        if (allCount > 0) {
            totalPage = allCount / rowCount;

            if (rowCount * totalPage < allCount) {
                totalPage++;
            }

            if (nowPage > totalPage) {
                nowPage = totalPage;
            }

            totalBlock = (totalPage - 1) / pageCount;
            nowBlock = (nowPage - 1) / pageCount;

            firstPage = 0;
            prevPage = 0;
            nextPage = 0;
            lastPage = 0;

            if (nowBlock > 0) {
                firstPage = 1;
            }

            if (nowPage > 1) {
                prevPage = nowPage - 1;
            }

            startPage = nowBlock * pageCount + 1;
            endPage = pageCount * (nowBlock + 1);

            if (endPage > totalPage)
                endPage = totalPage;

            if (nowPage < totalPage) {
                nextPage = nowPage + 1;
            }
            if (nowBlock < totalBlock) {
                lastPage = totalPage;
            }

            skipRowCount = (nowPage - 1) * rowCount;

        }

        this.page = nowPage;

//            this.startRowNum = startRowNum; // 시작레코드 번호 주입
//            this.endRowNum = endRowNum; // 종료레코드 번호 주입

        this.startPage = startPage; // 전체 페이지에서 시작 페이지
        this.endPage = endPage; // 전체 페이지에서 마지막 페이지

        this.firstPage = firstPage; // 현재 블럭에서 첫 페이지
        this.prevPage = prevPage; // 현재 블럭에서 이전 페이지
        this.nextPage = nextPage; // 현재 블럭에서 다음 페이지
        this.lastPage = lastPage; // 현재 블럭에서 마지막 페이지
        this.allCount = allCount; // 총 갯수
        this.skipRowCount = skipRowCount;
        return this;
    }


    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void setBody(List<T> list) {
        this.body = list;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getAllCount() {
        return allCount;
    }

    public void setAllCount(long allCount) {
        this.allCount = allCount;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
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

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public Object getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(Object searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public int getSkipRowCount() {
        return skipRowCount;
    }

    public void setSkipRowCount(int skipRowCount) {
        this.skipRowCount = skipRowCount;
    }
}
