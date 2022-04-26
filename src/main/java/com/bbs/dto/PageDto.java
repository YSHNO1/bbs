package com.bbs.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<PostDto> posts;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;  //当前页面
    private List<Integer> pages = new ArrayList<>(); //在页面上显示的页数
    private Integer totalPage;

    public void setPage(Integer totalCount, Integer page, Integer size) {
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page < 1) page = 1;
        if(page > totalPage) page = totalPage;
        this.page = page;
        //左边三个
        for(int i = 3; i > 0; i--){
            if(page - i > 0){
                pages.add(page - i);
            }
        }
        //当前页面
        pages.add(page);
        //右边三个
        for(int i = 1; i <= 3; i++){
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //如果当前在第一页，那么对应的前面的前一页的按钮是不显示的
        showPrevious = page != 1;
        //是否展示下一页
        showNext = page != totalPage;

        //是否展示第一页
        showFirstPage = !pages.contains(1);
        //是否展示最后一页
        showEndPage = !pages.contains(totalPage);
    }
}
