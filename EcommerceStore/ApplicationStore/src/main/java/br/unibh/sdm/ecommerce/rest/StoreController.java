package br.unibh.sdm.ecommerce.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.ecommerce.entity.Store;
import br.unibh.sdm.ecommerce.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Get all items from the store
     * @return List of all items
     */
    @Operation(summary = "Get all items", responses = {
        @ApiResponse(responseCode = "200", description = "List of store items",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Store.class)), mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<Store>> getAllItems() {
        return ResponseEntity.ok(storeService.getAllItems());
    }

    /**
     * Create a new item
     * @param item The item to create
     * @return The created item
     */
    @Operation(summary = "Create a new item", responses = {
        @ApiResponse(responseCode = "201", description = "Item created",
            content = @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Store> createItem(@RequestBody Store item) {
        Store createdItem = storeService.createItem(
            item.getCategory(),
            item.getDescription(),
            item.getItem(),
            item.getPrice(),
            item.getQuantity()
        );
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    /**
     * Find an item by its ID
     * @param id The ID of the item to find
     * @return The item if found, 404 if not found
     */
    @Operation(summary = "Find item by ID", responses = {
        @ApiResponse(responseCode = "200", description = "Item found",
            content = @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable String id) {
        Optional<Store> item = storeService.findById(id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find items by name
     * @param name The name of the item to search for
     * @return List of items matching the name
     */
    @Operation(summary = "Find items by name", responses = {
        @ApiResponse(responseCode = "200", description = "List of matching items",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Store.class)), mediaType = "application/json"))
    })
    @GetMapping("/search")
    public ResponseEntity<List<Store>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(storeService.findByName(name));
    }

    /**
     * Update an existing item by ID
     * @param id The ID of the item to update
     * @param item The new item data
     * @return 200 OK with updated item or 404 if not found
     */
    @Operation(summary = "Update item by ID", responses = {
        @ApiResponse(responseCode = "200", description = "Item updated",
            content = @Content(schema = @Schema(implementation = Store.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateItem(@PathVariable String id, @RequestBody Store item) {
        Optional<Store> updated = storeService.updateItem(id, item);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete an item by its ID
     * @param id The ID of the item to delete
     * @return 204 No Content if successful
     */
    @Operation(summary = "Delete item by ID", responses = {
        @ApiResponse(responseCode = "204", description = "Item deleted"),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        storeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all items
     * @return 204 No Content if successful
     */
    @Operation(summary = "Delete all items", responses = {
        @ApiResponse(responseCode = "204", description = "All items deleted")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        storeService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
