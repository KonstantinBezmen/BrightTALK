package com.bright.talk.domain.realm;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RealmDao extends CrudRepository<Realm, Long>, JpaSpecificationExecutor<Realm> {
}
