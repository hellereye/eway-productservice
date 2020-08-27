package com.mycloud.product.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacteristicValueMapperTest {

    private CharacteristicValueMapper characteristicValueMapper;

    @BeforeEach
    public void setUp() {
        characteristicValueMapper = new CharacteristicValueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(characteristicValueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(characteristicValueMapper.fromId(null)).isNull();
    }
}
