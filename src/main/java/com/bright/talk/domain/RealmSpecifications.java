package com.bright.talk.domain;

import org.springframework.data.jpa.domain.Specification;

public interface RealmSpecifications {
    Specification<Realm> nameEquals(String name);
}
