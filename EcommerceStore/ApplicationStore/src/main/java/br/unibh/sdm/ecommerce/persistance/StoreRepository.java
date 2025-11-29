package br.unibh.sdm.ecommerce.persistance;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.ecommerce.entity.Store;

@EnableScan
public interface StoreRepository extends CrudRepository<Store, String>  {
    List<Store> findByCategory(String category);
    List<Store> findByItem(String item);
    List<Store> findByPrice(Double price);
    List<Store> findByQuantity(Integer quantity);
    List<Store> findByDescription(String description);
}