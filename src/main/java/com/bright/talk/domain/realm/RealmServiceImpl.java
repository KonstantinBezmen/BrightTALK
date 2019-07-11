package com.bright.talk.domain.realm;

import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.Message;
import com.bright.talk.exception.ResourceNotFoundException;
import com.bright.talk.infrastructure.encode.KeyEncode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealmServiceImpl implements RealmService {

    private final RealmDao realmDao;

    private final RealmSpecifications realmSpecifications;

    public RealmServiceImpl(
        RealmDao realmDao,
        RealmSpecifications realmSpecifications
    ) {
        this.realmDao = realmDao;
        this.realmSpecifications = realmSpecifications;
    }

    @Override
    public Realm create(String name, String desc) throws BadRequestException {
        long count = realmDao.count(realmSpecifications.nameEquals(name));
        if (count != 0) {
            throw new BadRequestException(Message.DUPLICATE_REALM_NAME.getText());
        }
        Realm realm = new Realm()
            .setName(name)
            .setDescription(desc)
            .setKey(KeyEncode.generateKey());
        return realmDao.save(realm);
    }

    @Override
    public Realm get(Long realmId) throws ResourceNotFoundException {
        Optional<Realm> realmOptional = realmDao.findById(realmId);
        if (!realmOptional.isPresent()) {
            throw new ResourceNotFoundException(Message.REALM_NOT_FOUND.getText());
        }
        return realmOptional.get();
    }

    @Override
    public Realm get(String name) throws ResourceNotFoundException {
        Optional<Realm> realmOptional = realmDao.findOne(realmSpecifications.nameEquals(name));
        if (!realmOptional.isPresent()) {
            throw new ResourceNotFoundException(Message.REALM_NOT_FOUND.getText());
        }
        return realmOptional.get();
    }
}
