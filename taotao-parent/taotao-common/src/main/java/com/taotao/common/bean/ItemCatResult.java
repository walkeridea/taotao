package com.taotao.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ItemCatResult {

	@JsonProperty("data")//指定序列化json后的名称
	private List<ItemCatData> itemCats = new ArrayList<>();

	public List<ItemCatData> getItemCats() {
		return itemCats;
	}

	public void setItemCats(List<ItemCatData> itemCats) {
		this.itemCats = itemCats;
	}

}
