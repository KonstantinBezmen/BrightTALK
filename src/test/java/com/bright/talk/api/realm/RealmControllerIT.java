package com.bright.talk.api.realm;

import com.bright.talk.Application;
import com.bright.talk.api.IntegrationBase;
import com.bright.talk.domain.Realm;
import com.bright.talk.domain.RealmService;
import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.ResourceNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RealmControllerIT extends IntegrationBase {

    @Autowired
    private RealmService realmService;

    @Test
    public void get() {
        HashMap<String, Object> responseData = new HashMap<String, Object>() {
            {
                put("id", 1);
                put("name", "realm1");
                put("description", null);
            }
        };
        beforeTest(this::createRealm)
            .then()
            .sendRequestToEndpoint("/service/user/realm/1")
            .usingGetMethod()
            .andCheckThat()
            .responseCodeIsOk()
            .responseBodyContains(responseData);
    }

    private void createRealm() {
        try {
            realmService.create("realm1", null);
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_RealmNotFound() {
        sendRequestToEndpoint("/service/user/realm/2")
            .usingGetMethod()
            .andCheckThat()
            .responseCodeIsNotFound();
    }

    @Test
    public void get_RealmIdNotNumeric() {
        sendRequestToEndpoint("/service/user/realm/sdf")
            .usingGetMethod()
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    @Test
    public void create_RealmAlreadYExist() {
        beforeTest(this::createRealm)
            .then()
            .sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                    .setName("realm1")
                    .setDescription("desc1")
            )
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    @Test
    public void create_EmptyRealmName() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setName(" ")
                .setDescription("desc1")
            )
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    @Test
    public void create_RealmNameNotSupplied() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setDescription("desc1")
            )
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    @Test
    public void create() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(getRealmRequest())
            .andCheckThat()
            .responseCodeIsCreated()
            .and(this::realmWasCreated);
    }

    @Test
    public void create_RealmNameLengthLimit() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setName(RandomStringUtils.randomAlphabetic(64))
            )
            .andCheckThat()
            .responseCodeIsCreated();
    }

    @Test
    public void create_RealmNameLengthLimitFailed() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setName(RandomStringUtils.randomAlphabetic(65))
            )
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    @Test
    public void create_RealmDescLengthLimit() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setName("realm123")
                .setDescription(RandomStringUtils.randomAlphabetic(255))
            )
            .andCheckThat()
            .responseCodeIsCreated();
    }

    @Test
    public void create_RealmDescLengthLimitFailed() {
        sendRequestToEndpoint("/service/user/realm")
            .usingPostMethod()
            .withBody(new RealmCreateRequest()
                .setName("realm123")
                .setDescription(RandomStringUtils.randomAlphabetic(256))
            )
            .andCheckThat()
            .responseCodeIsBadRequest();
    }

    private RealmCreateRequest getRealmRequest() {
        return new RealmCreateRequest()
            .setName("realm2")
            .setDescription("desc2");
    }

    private void realmWasCreated() {
        RealmCreateRequest request = getRealmRequest();
        try {
            Realm realm = realmService.get(request.getName());
            assertNotNull(realm);
            assertEquals(request.getDescription(), realm.getDescription());
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }
}