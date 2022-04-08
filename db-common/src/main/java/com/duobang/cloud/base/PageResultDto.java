package com.duobang.cloud.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDto<T> extends BasePageDto{

    public PageResultDto(Long totalCount, Long pageSize, Long currPage, List<T> list){
        super(currPage, pageSize);
        this.totalCount = totalCount;
        this.list = list;
    }
    private Long totalCount;
    private List<T> list;
}
