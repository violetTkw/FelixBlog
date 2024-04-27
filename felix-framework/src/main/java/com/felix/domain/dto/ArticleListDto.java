package com.felix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:ArticleListDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/23 11:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDto {
    //标题
    private String title;
    //文章摘要
    private String summary;
}
