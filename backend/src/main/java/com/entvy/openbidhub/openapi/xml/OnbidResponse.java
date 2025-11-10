package com.entvy.openbidhub.openapi.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnbidResponse {

    @XmlElement(name = "body")
    private OnbidBody body;

    public OnbidBody getBody() {
        return body;
    }

    public void setBody(OnbidBody body) {
        this.body = body;
    }
}
