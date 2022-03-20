package com.kaspper.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {

    private Long pageNum;
    private Long pageItemsNum;
    private Long totalItems;
    private Long totalPages;
    private List<T> data;

    public static <K, E> PageDTO<E> buildPageDTO(Long pageNum, Page<K> models, List<E> data){
        PageDTO<E> page = new PageDTO<>();
        page.setPageNum(pageNum != null ? pageNum : 1);
        page.setTotalPages((long) models.getTotalPages());
        page.setTotalItems(models.getTotalElements());
        page.setPageItemsNum((long) models.getContent().size());
        page.setData(data);

        return page;
    }

}
