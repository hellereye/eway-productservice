package com.mycloud.product.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecificationMapperTest {

    private SpecificationMapper specificationMapper;

    @BeforeEach
    public void setUp() {
        specificationMapper = new SpecificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specificationMapper.fromId(null)).isNull();
    }
}
