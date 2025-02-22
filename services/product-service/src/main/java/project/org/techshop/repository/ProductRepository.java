package project.org.techshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.org.techshop.entity.Category;
import project.org.techshop.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT c FROM Category c WHERE c.id = ?1")
    Optional<Category> findCategoryById(Long categoryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Photo p WHERE p.id = ?1")
    void deletePhotoById(Long id);
}
