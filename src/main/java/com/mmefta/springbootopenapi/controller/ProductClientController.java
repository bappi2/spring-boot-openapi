package com.mmefta.springbootopenapi.controller;

import com.mmefta.springbootopenapi.client.ProductServiceClient;
import com.mmefta.springbootopenapi.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-client/product")
public class ProductClientController {

    @Autowired
    private ProductServiceClient productServiceClient;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productServiceClient.saveProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productServiceClient.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productServiceClient.getProductById(id);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productServiceClient.updateProduct(product);
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable int product) {
        return productServiceClient.deleteProduct(product);
    }

}
