package com.taotao.manage.pojo;

import javax.persistence.*;

/**
 * 改方法中的属性和数据库中的字段作映射：
 * 要求：1、驼峰命名    自动与数据库中的字段映射    eg:sellPoint  sell_point
 *      2、自己指定  属性上写注解 @Column(name = "sub_title")
 *      3、该属性不是数据库字段    @Transient注解
 *      4、一定要有 @Id 作为主键  加上自增主键的回显 @GeneratedValue(strategy = GenerationType.IDENTITY)
 */
@Table(name = "tb_item")
public class Item extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "sell_point")
    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private String image;

    private Long cid;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price
                + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
                + status + "]";
    }

}
