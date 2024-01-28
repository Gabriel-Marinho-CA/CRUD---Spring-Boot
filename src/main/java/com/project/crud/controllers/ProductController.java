package com.project.crud.controllers;

import com.project.crud.domain.product.Product;
import com.project.crud.domain.product.ProductRepository;
import com.project.crud.domain.product.RequestProducts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {

        var allProducts = repository.findAll();

        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProducts data) {

        Product newProduct = new Product(data);

        repository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity updateProduct( @RequestBody @Valid RequestProducts data) {
        Product product = repository.getReferenceById((String) data.id());
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());

        repository.save(product);
//        repository.save(product);

        return  ResponseEntity.ok(data);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.badRequest().body("id not found");
        }

        Product deletedProduct = repository.findById(id).orElse(null);

        repository.deleteById(id);

        return ResponseEntity.ok(deletedProduct);
    }
}
