package br.com.thamasia.crud_system.repository;

import br.com.thamasia.crud_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
