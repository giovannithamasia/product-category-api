package br.com.thamasia.crud_system.repository;

import br.com.thamasia.crud_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryIdCategory(Long idCategory);

    boolean existsByCategoryIdCategory(Long idCategory);

    List<Product> findByNameProductStartingWithIgnoreCase(String nameProduct);
}