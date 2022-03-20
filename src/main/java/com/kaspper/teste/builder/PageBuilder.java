package com.kaspper.teste.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageBuilder {

    private static final int DEFAULT_PAGE_SIZE = 10;

    public static Pageable from(Integer pageNumber, Integer pageSize){
        if(pageSize == null || pageSize == 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if(pageNumber == null){
            pageNumber = 0;
        } else if (pageNumber > 0){
            pageNumber--;
        }

        return PageRequest.of(pageNumber, pageSize);
    }
}
