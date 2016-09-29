package me.fabiomaffioletti.msc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.fabiomaffioletti.msc.util.HttpEntityBuilder;
import me.fabiomaffioletti.msc.dto.ProductDTO;

@Log4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final RestTemplate restTemplate;

    @Value("${remote.server.uri.products}")
    private String productsURI;

    @Override
    public ResponseEntity<List<ProductDTO>> findAll() {
        HttpEntityBuilder<Void> httpEntityBuilder = new HttpEntityBuilder<>();
        return restTemplate.exchange(productsURI, HttpMethod.GET, httpEntityBuilder.build(), new ParameterizedTypeReference<List<ProductDTO>>() {});
    }

    @Override
    public ResponseEntity<ProductDTO> findOne(Long id) {
        HttpEntityBuilder<Void> httpEntityBuilder = new HttpEntityBuilder<>();
        return restTemplate.exchange(productsURI + "/{id}", HttpMethod.GET, httpEntityBuilder.build(), ProductDTO.class, id);
    }

}
