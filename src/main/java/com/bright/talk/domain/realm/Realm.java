package com.bright.talk.domain.realm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "realm")
public class Realm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "`key`")
    private String key;

    public Long getId() {
        return id;
    }

    public Realm setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Realm setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Realm setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Realm setKey(String key) {
        this.key = key;
        return this;
    }
}
