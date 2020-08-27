package com.mycloud.product.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class CharacteristicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CharacteristicDTO.class);
        CharacteristicDTO characteristicDTO1 = new CharacteristicDTO();
        characteristicDTO1.setId(1L);
        CharacteristicDTO characteristicDTO2 = new CharacteristicDTO();
        assertThat(characteristicDTO1).isNotEqualTo(characteristicDTO2);
        characteristicDTO2.setId(characteristicDTO1.getId());
        assertThat(characteristicDTO1).isEqualTo(characteristicDTO2);
        characteristicDTO2.setId(2L);
        assertThat(characteristicDTO1).isNotEqualTo(characteristicDTO2);
        characteristicDTO1.setId(null);
        assertThat(characteristicDTO1).isNotEqualTo(characteristicDTO2);
    }
}
