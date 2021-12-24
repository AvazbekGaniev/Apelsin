package apelsin.repository;

import apelsin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);

    @Query(value = "select c.name from category c inner join product p on c.id = p.category_id where p.id=:id",nativeQuery = true)
    Optional<?> findByProductId(@Param("id") Integer id);
}
