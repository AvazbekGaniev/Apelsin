package apelsin.repository;

import apelsin.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    @Query(value = "select * from invoice i where i.issued>i.due",nativeQuery = true)
    List<Invoice>getAllInvoiceIssued();

    @Query(value = "select i.id from invoice i inner join orders o on o.id = i.order_id where o.date >i.issued",nativeQuery = true)
    List<?> getInvoiceWrongDate();

    @Query(value = "select o.customer_id from orders o inner join invoice i on o.id = i.order_id inner join payment p on i.id = p.invoice_id where i.amount<p.amount",nativeQuery = true)
    List<?>getOverallInvoicePaid();

}
