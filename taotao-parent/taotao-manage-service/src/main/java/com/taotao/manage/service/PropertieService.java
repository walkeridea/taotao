package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *  @Value 只能在当前容器中获取值，子容器(springmvc)可以访问父容器(spring)，父容器不能访问子容器。
 *  在controller 中，如果直接去Value值，需要在当前容器中加入该配置文件(不推荐)。
 *  2、可以放到service 中，这样子容器controller中取得sercice父容器的值，间接取到
 */
@Service
public class PropertieService {
    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;

    @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;
}
