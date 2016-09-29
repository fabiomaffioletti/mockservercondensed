package me.fabiomaffioletti.msc.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import me.fabiomaffioletti.msc.dto.ProductDTO;

/**
 * Created by fmaffioletti on 9/29/16.
 */
public interface ProductRepository {

    ResponseEntity<List<ProductDTO>> findAll();

    ResponseEntity<ProductDTO> findOne(Long id);

}
