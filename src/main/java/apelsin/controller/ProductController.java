package apelsin.controller;

import apelsin.entity.Product;
import apelsin.payload.ApiResponse;
import apelsin.payload.ProductDto;
import apelsin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.add(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        List<Product> all = productService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getOne(@PathVariable int id) {
        ApiResponse apiResponse = productService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable int id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> infoProductByCustomerId(@RequestParam int id) {
        ApiResponse apiResponse = productService.infoProductCustomerId(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/moreProductTen")
    public HttpEntity<?> getProductMoreTen() {
        ApiResponse apiResponse = productService.moreTen();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/quantityEight")
    public HttpEntity<?> getProductQuantityEight() {
        ApiResponse apiResponse = productService.getProductQuantityEight();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getTenExpensive")
    public HttpEntity<?> getTenExpensive(){
        ApiResponse apiResponse = productService.getTenProductExpensive();
        return ResponseEntity.ok(apiResponse);
    }

}
