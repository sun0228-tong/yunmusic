package com.wangyi.search.api;

import com.wangyi.common.vo.R;
import com.wangyi.search.service.NewsIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: OneDrive
 * @description:
 * @author: Feri
 * @create: 2019-09-25 17:42
 */
@RestController
@Api(value = "操作新闻",tags = "操作新闻")
public class NewsIndexController {
    @Autowired
    private NewsIndexService newsIndexService;

    @ApiOperation(value = "实现新闻的全部查询",notes = "实现新闻的全部查询 分页")
    @GetMapping("/api/search/newsall.do")
    public R queryAll(int page, int size){
        return newsIndexService.queryAll(page, size);
    }
}
