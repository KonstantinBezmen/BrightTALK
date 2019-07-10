package com.bright.talk.api.realm;

import com.bright.talk.domain.Realm;
import com.bright.talk.domain.RealmService;
import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.ResourceNotFoundException;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RealmControllerTest {

    private RealmController realmController;

    @Mock
    private RealmService realmService;

    private Realm realm;

    @Before
    public void setUp() {
        realmController = new RealmController(
            realmService,
            new DozerBeanMapper()
        );
        realm = new Realm()
            .setId(1L)
            .setName("name")
            .setDescription("desc")
            .setKey("key");
    }

    @Test
    public void get() throws Exception {
        when(realmService.get(1L)).thenReturn(realm);
        RealmResponse realmResponse = realmController.get("1");
        assertNotNull(realmResponse);
        assertEquals(realm.getId(), realmResponse.getId());
        assertEquals(realm.getName(), realmResponse.getName());
        assertEquals(realm.getDescription(), realmResponse.getDescription());
        assertEquals(realm.getKey(), realmResponse.getKey());
    }

    @Test(expected = BadRequestException.class)
    public void get_RealmIdIsNotNumeric() throws Exception {
        realmController.get("asd");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_RealmNotFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(realmService).get(1L);
        realmController.get("1");
    }

    @Test
    public void create() throws Exception {
        when(realmService.create(realm.getName(), realm.getDescription()))
            .thenReturn(realm);
        RealmResponse realmResponse = realmController.create(
            new RealmCreateRequest()
                .setName(realm.getName())
                .setDescription(realm.getDescription())
        );
        assertNotNull(realmResponse);
        assertEquals(realm.getId(), realmResponse.getId());
        assertEquals(realm.getName(), realmResponse.getName());
        assertEquals(realm.getDescription(), realmResponse.getDescription());
        assertEquals(realm.getKey(), realmResponse.getKey());
    }

    @Test
    public void create_EmptyDescription() throws Exception {
        Realm realm = new Realm()
            .setId(2L)
            .setName("name2")
            .setKey("key2");
        when(realmService.create(realm.getName(), null))
            .thenReturn(realm);
        RealmResponse realmResponse = realmController.create(
            new RealmCreateRequest()
                .setName(realm.getName())
        );
        assertNotNull(realmResponse);
        assertEquals(realm.getId(), realmResponse.getId());
        assertEquals(realm.getName(), realmResponse.getName());
        assertNull(realmResponse.getDescription());
        assertEquals(realm.getKey(), realmResponse.getKey());
    }

    @Test(expected = BadRequestException.class)
    public void create_EmptyRealmName() throws Exception {
        realmController.create(
            new RealmCreateRequest()
                .setName(" ")
                .setDescription(realm.getDescription())
        );
    }

    @Test(expected = BadRequestException.class)
    public void create_RealmNameNotSupplied() throws Exception {
        realmController.create(
            new RealmCreateRequest()
                .setDescription(realm.getDescription())
        );
    }

}