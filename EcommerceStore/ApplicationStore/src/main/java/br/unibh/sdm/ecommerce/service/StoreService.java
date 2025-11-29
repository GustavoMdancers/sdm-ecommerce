package br.unibh.sdm.ecommerce.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.ecommerce.entity.Store;
import br.unibh.sdm.ecommerce.persistance.StoreRepository;

@Service
public class StoreService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final StoreRepository storeRepo;

    public StoreService(StoreRepository storeRepo) {
        this.storeRepo = storeRepo;
    }

    public List<Store> getAllItems() {
        logger.info("Buscando todos os itens da loja no banco de dados");
        List<Store> items = new ArrayList<>();
        storeRepo.findAll().forEach(items::add);
        return items;
    }

    /**
     * Creates a new store item in the database
     * @param category    The category of the item
     * @param description The description of the item
     * @param item       The name of the item
     * @param price      The price of the item
     * @param quantity   The quantity in stock
     * @return The created store item with generated ID
     */
    public Store createItem(String category, String description, String item, Double price, Integer quantity) {
        logger.info("Criando novo item na loja: {}", item);
        Store newItem = new Store(category, description, item, price, quantity);
        return storeRepo.save(newItem);
    }
    /**
     * Find a store item by its ID
     * @param id The ID of the item to find
     * @return Optional containing the item if found, empty otherwise
     */
    public Optional<Store> findById(String id) {
        logger.info("Buscando item por ID: {}", id);
        Objects.requireNonNull(id, "id must not be null");
        return storeRepo.findById(id);
    }
    

    /**
     * Update an existing store item by ID. Only fields present in the provided
     * Store object will be copied over (all fields are overwritten here).
     * @param id The ID of the item to update
     * @param updated The new values for the item
     * @return Optional containing the updated item if it existed, empty otherwise
     */
    public Optional<Store> updateItem(String id, Store updated) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(updated, "updated must not be null");
        logger.info("Atualizando item com ID: {}", id);
        Optional<Store> existing = storeRepo.findById(id);
        if (!existing.isPresent()) {
            return Optional.empty();
        }
        Store e = existing.get();
        e.setCategory(updated.getCategory());
        e.setDescription(updated.getDescription());
        e.setItem(updated.getItem());
        e.setPrice(updated.getPrice());
        e.setQuantity(updated.getQuantity());
        Store saved = storeRepo.save(e);
        return Optional.of(saved);
    }

    /**
     * Find store items by name
     * @param name The name of the item to search for
     * @return List of items matching the name
     */
    public List<Store> findByName(String name) {
        logger.info("Buscando itens por nome: {}", name);
        return storeRepo.findByItem(name);
    }

    /**
     * Delete a store item by its ID
     * @param id The ID of the item to delete
     */
    public void deleteById(String id) {
        logger.info("Deletando item com ID: {}", id);
        Objects.requireNonNull(id, "id must not be null");
        storeRepo.deleteById(id);
    }

    /**
     * Delete all items from the store
     */
    public void deleteAll() {
        logger.info("Deletando todos os itens da loja");
        storeRepo.deleteAll();
    }
}
