package apelsin.service;

import apelsin.entity.*;
import apelsin.payload.ApiResponse;
import apelsin.payload.OrderDTO;
import apelsin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse addOrder(OrderDTO orderDto) {
        Order order = new Order();
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustId());
        if (!optionalCustomer.isPresent()) return new ApiResponse("Not found", false);
        order.setCustomer(optionalCustomer.get());
        order.setDate(new Date());
        Order save = orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(order);
        Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
        if (!optionalProduct.isPresent()) return new ApiResponse("Failed ", false);
        Product product = optionalProduct.get();
        detail.setProduct(product);
        detail.setQuantity(orderDto.getQuantity());
        detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setOrder(save);
        invoice.setAmount(product.getPrice() * orderDto.getQuantity());
        invoice.setIssued(new Date());
        invoice.setDue(orderDto.getDue());
        invoiceRepository.save(invoice);

        return new ApiResponse("Succes", true);
    }


    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public ApiResponse getOne(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Not found", false);
        return new ApiResponse("ok", true, optionalOrder.get());
    }

    public ApiResponse deleteOrder(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Not found", false);
        orderRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public List<Order> getOrder(int id) {
        return orderRepository.findAllBy(id);
    }

    public ApiResponse lastOrders() {
        List<?> lastOrders = orderRepository.getLastOrders();
        return new ApiResponse("Success", true, lastOrders);
    }

    public ApiResponse getOrdersWithoutDetail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = dateFormat.format(date);
        List<?> ordersWithoutDetails = orderRepository.getOrdersWithoutDetails(date);
        return new ApiResponse("Success", true, ordersWithoutDetails);
    }

    public ApiResponse getDetailOrder(int id) {
        List<?> detailsByOrderId = orderRepository.getDetailsByOrderId(id);
        return new ApiResponse("Success", true, detailsByOrderId);
    }

    public ApiResponse getAllOrderByDate(Date dateFrom, Date dateTo) {
        List<Order> allByDateBetween = orderRepository.findAllByDateBetween(dateFrom, dateTo);
        return new ApiResponse("Success",true,allByDateBetween);
    }
}
