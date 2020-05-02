package com.share.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {


    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> items;// 当前页数据

}
