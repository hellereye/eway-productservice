package com.mycloud.product.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class CharacteristicValueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CharacteristicValueDTO.class);
        CharacteristicValueDTO characteristicValueDTO1 = new CharacteristicValueDTO();
        characteristicValueDTO1.setId(1L);
        CharacteristicValueDTO characteristicValueDTO2 = new CharacteristicValueDTO();
        assertThat(characteristicValueDTO1).isNotEqualTo(characteristicValueDTO2);
        characteristicValueDTO2.setId(characteristicValueDTO1.getId());
        assertThat(characteristicValueDTO1).isEqualTo(characteristicValueDTO2);
        characteristicValueDTO2.setId(2L);
        assertThat(characteristicValueDTO1).isNotEqualTo(characteristicValueDTO2);
        characteristicValueDTO1.setId(null);
        assertThat(characteristicValueDTO1).isNotEqualTo(characteristicValueDTO2);
    }
}
