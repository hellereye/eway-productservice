package com.mycloud.product.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacteristicMapperTest {

    private CharacteristicMapper characteristicMapper;

    @BeforeEach
    public void setUp() {
        characteristicMapper = new CharacteristicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(characteristicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(characteristicMapper.fromId(null)).isNull();
    }
}
