package com.taotao.manage.service;

import com.taotao.manage.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryService extends BaseService<ContentCategory> {

    public void saveContentCategory(ContentCategory contentCategory){
        contentCategory.setId(null);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        this.save(contentCategory);
        //判断父节点的isParent是否为true,不是，需要修改为true
        ContentCategory parent=this.queryById(contentCategory.getParentId());
        if(!parent.getIsParent()){
            parent.setIsParent(true);
            this.updateSelective(parent);
        }

    }
    public void deleteContentCategory(ContentCategory contentCategory){
        //查找所有的子节点
        List<Object> ids=new ArrayList<>();
        ids.add(contentCategory.getId());
        findAllSubNode(contentCategory.getId(),ids);

        //删除所有的子节点
        this.deleteByIds(ContentCategory.class,"id",ids);
        //判断当前结点的父节点是否含有其他的子节点，如果没有，修改isparent为false
        ContentCategory record=new ContentCategory();
        record.setParentId(contentCategory.getParentId());
        List<ContentCategory> list=this.queryListByWhere(record);
        if(null==list || list.isEmpty()){
            ContentCategory parent=new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            this.updateSelective(parent);
        }

    }
    private void findAllSubNode(Long parentId,List<Object> ids){
        ContentCategory record=new ContentCategory();
        record.setParentId(parentId);
        List<ContentCategory> list=this.queryListByWhere(record);
        for(ContentCategory contentCategory:list){
            ids.add(contentCategory.getId());
            //判断该节点是否为父节点，如果是，进行递归
            if(contentCategory.getIsParent()){
                findAllSubNode(contentCategory.getId(),ids);
            }
        }
    }


}
