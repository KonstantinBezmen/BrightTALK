package com.bright.talk.api.realm;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "realm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"description", "key"})
@JsonPropertyOrder({"id", "name", "description", "key"})
public class RealmResponse {
    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String name;

    private String description;

    private String key;

    public Long getId() {
        return id;
    }

    public RealmResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RealmResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RealmResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getKey() {
        return key;
    }

    public RealmResponse setKey(String key) {
        this.key = key;
        return this;
    }
}
