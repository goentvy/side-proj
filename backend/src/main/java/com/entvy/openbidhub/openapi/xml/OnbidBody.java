package com.entvy.openbidhub.openapi.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OnbidBody {
    @XmlElement(name = "items")
    private OnbidItems items;

    public OnbidItems getItems() {
        return items;
    }

    public void setItems(OnbidItems items) {
        this.items = items;
    }
}
