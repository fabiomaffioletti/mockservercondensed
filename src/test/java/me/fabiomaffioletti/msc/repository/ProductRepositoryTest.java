package me.fabiomaffioletti.msc.repository;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import me.fabiomaffioletti.msc.MockserverCondensedTestConfiguration;
import me.fabiomaffioletti.msc.dto.ProductDTO;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@MockserverCondensedTestConfiguration
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testItShouldRetrieveTheListOfProducts() throws IOException {
        ResponseEntity<List<ProductDTO>> responseEntity = productRepository.findAll();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), hasSize(3));

        responseEntity = productRepository.findAll();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), hasSize(3));
    }

    @Test
    public void testItShouldRetrieveAProductWithTheGivenId() throws IOException {
        ResponseEntity<ProductDTO> responseEntity = productRepository.findOne(1L);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(1L));
        assertThat(responseEntity.getBody().getName(), is(equalTo("product one")));

        responseEntity = productRepository.findOne(2L);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(2L));
        assertThat(responseEntity.getBody().getName(), is(equalTo("product two")));
    }

}
