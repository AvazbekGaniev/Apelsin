package apelsin.repository;

import apelsin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query(value = "select p.amount ,p.time from payment p inner join invoice i on i.id = p.invoice_id where invoice_id=(select o.id from invoice i natural join orders o where o.id=:orderId)",nativeQuery = true)
    List<Payment> findAllByOrderId(@RequestParam("orderId") Integer orderId);
}
