package com.example.luntan.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if (totalCount % size==0){
            totalPage=totalCount/size;
        }
        else {
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page=page;
        for (int i = page ; i <= page+3 ; i++) {
            if (i<=totalPage) {
                pages.add(i);
            }
        }
        for (int i = page-3; i < page; i++) {
            if (i>0){
                pages.add(i);
            }
        }
        //排序
        Collections.sort(pages);
        //this.page=page;

        //是否展示第一页
        if (page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        //是否展示最后一页
        if (page== totalPage){
            showNext=false;
        }else {
            showNext=true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {

            showEndPage=true;
        }

    }
}
