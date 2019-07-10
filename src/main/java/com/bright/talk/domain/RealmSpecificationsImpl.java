package com.bright.talk.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RealmSpecificationsImpl implements RealmSpecifications {

    @Override
    public Specification<Realm> nameEquals(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }
}
