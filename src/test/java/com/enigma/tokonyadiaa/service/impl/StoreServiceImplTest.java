package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.model.response.StoreResponse;
import com.enigma.tokonyadiaa.repository.StoreRepository;
import com.enigma.tokonyadiaa.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StoreServiceImplTest {

    //mock repository
    private final StoreRepository storeRepository = mock(StoreRepository.class);

    //create service instance
    private  final StoreService storeService = new StoreServiceImpl(storeRepository);
    @Test
    void itShouldReturnWhenCreateNewStore() {
        Store dummyStore = new Store();
        dummyStore.setId("123");
        dummyStore.setName("mantap betul");

        //mock behavior repository
        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore);

        //perform
        Store createStore = storeService.create(dummyStore);

        verify(storeRepository, times(1)).save(dummyStore);

        assertEquals(dummyStore.getId(), createStore.getId());
        assertEquals(dummyStore.getName(), createStore.getName());
    }

    @Test
    void itShouldGetDataStoreOneWhenGetByIdStore() {
        Store dummyStore = new Store();
        dummyStore.setId("123");
        dummyStore.setName("mantap betul");

        //mo behavior storeRepository findById

        when(storeRepository.findById(dummyStore.getId())).thenReturn(Optional.of(dummyStore));

        //perform
        Store actualStore = storeService.getById(dummyStore.getId());

        //verify
        verify(storeRepository, times(1)).findById(dummyStore.getId());

        //verify ekcpected and actual
        assertEquals(dummyStore.getId(), actualStore.getId());
        assertEquals(dummyStore.getName(),actualStore.getName());

    }

    @Test
    void itShouldGetAllDataStoreWhenCallGetAll() {
        List<Store> dummyStore = new ArrayList<>();
        dummyStore.add(new Store("1","123","berkahya nak","jakarta", "089643354357"));
        dummyStore.add(new Store("2","124","berka","sumatra", "089dsf43354357"));
        dummyStore.add(new Store("3","125","berkahsi","bali", "08967643354357"));

        when(storeRepository.findAll()).thenReturn(dummyStore);

        List<StoreResponse> actualStore = storeService.getAll();

        verify(storeRepository,times(1)).findAll();
        for(int i =0; i< dummyStore.size(); i++){
            assertEquals(dummyStore.get(i).getId() , actualStore.get(i).getId());
            assertEquals(dummyStore.get(i).getName() , actualStore.get(i).getStoreName());
        }

        assertEquals(dummyStore.size(), actualStore.size());



    }

    @Test
    void update() {
        /*Store dummyStoreUpdate = new Store("1","123","berkah ya le","semrang wae","08945678432");
        when(storeRepository.findById(dummyStoreUpdate.getId())).thenReturn(Optional.of(new Store("1","123","tokoku","jakarta","0896546477")));
        when(storeRepository.save(dummyStoreUpdate)).thenReturn(dummyStoreUpdate);

        Store updateStore = storeService.update(dummyStoreUpdate);

        verify(storeRepository,times(1)).findById(dummyStoreUpdate.getId());
        verify(storeRepository,times(1)).save(dummyStoreUpdate);

        assertEquals(dummyStoreUpdate.getNoSiup(),updateStore.getNoSiup());
        assertEquals(dummyStoreUpdate.getName(),updateStore.getName());

        System.out.println("Data " + dummyStoreUpdate.getName() +"" + updateStore.getName() );


        */


        // Create a new store
        Store store = new Store();
        store.setId("storeId"); // Ganti dengan ID toko yang ingin Anda perbarui
        store.setName("My Store");
        store.setAddress("123 Main Street");

        // Mock behavior repository
        when(storeRepository.findById(store.getId())).thenReturn(Optional.of(store));
        when(storeRepository.save(store)).thenReturn(store);

        // Update the store
        store.setName("My New Store");

        // Perform
        Store updatedStore = storeService.update(store);

        // Verify
        verify(storeRepository, times(1)).findById(store.getId());
        verify(storeRepository, times(1)).save(store);

        // Assert
        assertEquals("My New Store", updatedStore.getName());

    }

    @Test
    void delete() {
        String storeId = "1";
        storeService.deleteById(storeId);


        verify(storeRepository, times(1)).deleteById(storeId);



    }


}