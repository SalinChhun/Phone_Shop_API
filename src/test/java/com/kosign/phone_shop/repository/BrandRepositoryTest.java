package com.kosign.phone_shop.repository;

import com.kosign.phone_shop.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;
    @Test
    public void testFindByName() {
        //Given
        Brand brand = new Brand();
        brand.setName("Apple");
        brandRepository.save(brand); 
        //When
        List<Brand> brands = brandRepository.findByNameLike("%A%");
        assertEquals(1, brands.size());
        assertEquals("Apple", brands.get(0).getName());
        assertEquals(1,brands.get(0).getId());
        //Then

    }
}
