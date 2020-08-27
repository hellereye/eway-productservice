package com.mycloud.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class CharacteristicValueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CharacteristicValue.class);
        CharacteristicValue characteristicValue1 = new CharacteristicValue();
        characteristicValue1.setId(1L);
        CharacteristicValue characteristicValue2 = new CharacteristicValue();
        characteristicValue2.setId(characteristicValue1.getId());
        assertThat(characteristicValue1).isEqualTo(characteristicValue2);
        characteristicValue2.setId(2L);
        assertThat(characteristicValue1).isNotEqualTo(characteristicValue2);
        characteristicValue1.setId(null);
        assertThat(characteristicValue1).isNotEqualTo(characteristicValue2);
    }
}
