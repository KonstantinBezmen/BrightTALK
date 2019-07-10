package com.bright.talk.domain;

import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RealmServiceImplTest {

    private RealmServiceImpl service;

    @Mock
    private RealmDao realmDao;

    @Mock
    private RealmSpecifications specifications;

    @Before
    public void before() {
        service = new RealmServiceImpl(realmDao, specifications);
    }

    @Test
    public void create() throws Exception {
        String name = "name";
        String description = "desc";

        Specification<Realm> specification = mock(Specification.class);
        when(specifications.nameEquals(name)).thenReturn(specification);
        when(realmDao.count(specification)).thenReturn(0L);

        service.create(name, description);

        ArgumentCaptor<Realm> argumentCaptor = ArgumentCaptor.forClass(Realm.class);
        verify(realmDao, times(1)).save(argumentCaptor.capture());
        Realm realm = argumentCaptor.getValue();
        assertNotNull(realm);
        assertEquals(name, realm.getName());
        assertEquals(description, realm.getDescription());
    }

    @Test(expected = BadRequestException.class)
    public void create_RealmWithTheSameNameAlreadyExist() throws Exception {
        String name = "name";
        Specification<Realm> specification = mock(Specification.class);
        when(specifications.nameEquals(name)).thenReturn(specification);
        when(realmDao.count(specification)).thenReturn(1L);
        service.create(name, "desc");
    }

    @Test
    public void get() throws Exception {
        Realm realm1 = new Realm()
            .setId(1L)
            .setName("name")
            .setDescription("desc")
            .setKey("key");
        when(realmDao.findById(1L)).thenReturn(Optional.of(realm1));

        Realm realm2 = service.get(1L);
        assertNotNull(realm2);
        assertEquals(realm1.getId(), realm2.getId());
        assertEquals(realm1.getName(), realm2.getName());
        assertEquals(realm1.getDescription(), realm2.getDescription());
        assertEquals(realm1.getKey(), realm2.getKey());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByName_RealmNotFound() throws Exception {
        String name = "name";
        Specification<Realm> specification = mock(Specification.class);
        when(specifications.nameEquals(name)).thenReturn(specification);
        when(realmDao.findOne(specification)).thenReturn(Optional.empty());
        service.get(name);
    }

    @Test
    public void getByName() throws Exception {
        String name = "name";
        Specification<Realm> specification = mock(Specification.class);
        when(specifications.nameEquals(name)).thenReturn(specification);
        Realm realm1 = new Realm()
            .setId(1L)
            .setName("name")
            .setDescription("desc")
            .setKey("key");
        when(realmDao.findOne(specification)).thenReturn(Optional.of(realm1));

        Realm realm2 = service.get(name);
        assertNotNull(realm2);
        assertEquals(realm1.getId(), realm2.getId());
        assertEquals(realm1.getName(), realm2.getName());
        assertEquals(realm1.getDescription(), realm2.getDescription());
        assertEquals(realm1.getKey(), realm2.getKey());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_RealmNotFound() throws Exception {
        when(realmDao.findById(1L)).thenReturn(Optional.empty());
        service.get(1L);
    }
}
