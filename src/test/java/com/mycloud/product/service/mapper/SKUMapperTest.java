package com.mycloud.product.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SKUMapperTest {

    private SKUMapper sKUMapper;

    @BeforeEach
    public void setUp() {
        sKUMapper = new SKUMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sKUMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sKUMapper.fromId(null)).isNull();
    }
}
