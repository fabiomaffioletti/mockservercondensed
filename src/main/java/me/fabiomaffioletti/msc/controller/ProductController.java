package me.fabiomaffioletti.msc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.fabiomaffioletti.msc.dto.ProductDTO;
import me.fabiomaffioletti.msc.repository.ProductRepository;

@Log4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findOne(@PathVariable Long id) {
        return productRepository.findOne(id);
    }

}
