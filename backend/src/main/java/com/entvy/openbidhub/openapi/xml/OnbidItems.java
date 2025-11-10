package com.entvy.openbidhub.openapi.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OnbidItems {

    @XmlElement(name = "item")
    private List<OnbidItemRawDto> itemList;

    public List<OnbidItemRawDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<OnbidItemRawDto> itemList) {
        this.itemList = itemList;
    }

}
