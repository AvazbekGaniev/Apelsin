package apelsin.repository;

import apelsin.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail,Integer> {
    Optional<Detail> findByOrder_Id(Integer id);
}
