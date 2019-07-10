package com.bright.talk.domain;

import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.ResourceNotFoundException;

public interface RealmService {
    /**
     * Returns realm by identifier.
     *
     * @param realmId identifier
     * @return Realm
     * @throws ResourceNotFoundException when realm with this id doesn't exist
     */
    Realm get(Long realmId) throws ResourceNotFoundException;

    /**
     * Creates realm.
     *
     * @param name realm name
     * @param desc realm description
     * @return Realm
     * @throws BadRequestException when realm with the same name already exist
     */
    Realm create(String name, String desc) throws BadRequestException;
}
