package com.taotao.manage.pojo;

import javax.persistence.*;

@Table(name = "tb_item_param_item")
public class ItemParamItem extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //该值自动生成
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "param_data")
    private String paramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

}
