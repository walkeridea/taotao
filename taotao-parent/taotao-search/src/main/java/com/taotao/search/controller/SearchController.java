package com.taotao.search.controller;

import com.taotao.search.bean.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("q")String keyWords,
                               @RequestParam(value = "page",defaultValue = "1") Integer page){

        ModelAndView mv = new ModelAndView("search");

        try {
            //解决GET请求中文乱码问题
            keyWords = new String(keyWords.getBytes("ISO-8859-1"),"utf-8");
            //搜索商品
            SearchResult searchResult=this.searchService.search(keyWords, page);
            mv.addObject("query", keyWords);//搜索关键字
            mv.addObject("itemList", searchResult.getData());//商品列表
            mv.addObject("page",page);//页数
            int total=searchResult.getTotal().intValue();
            int rows=searchService.ROWS;
            int pages = total % rows == 0 ? total / rows : total / rows + 1;
            mv.addObject("pages",pages);//总共页数
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;

    }
}
