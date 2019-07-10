package com.bright.talk.api.realm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.Size;

@XmlRootElement(name = "realm")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealmCreateRequest {
    @XmlAttribute
    @Size(max = 64)
    private String name;
    @Size(max = 255)
    private String description;

    public String getName() {
        return name;
    }

    public RealmCreateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RealmCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }
}
