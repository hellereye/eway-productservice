package com.mycloud.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class SKUTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SKU.class);
        SKU sKU1 = new SKU();
        sKU1.setId(1L);
        SKU sKU2 = new SKU();
        sKU2.setId(sKU1.getId());
        assertThat(sKU1).isEqualTo(sKU2);
        sKU2.setId(2L);
        assertThat(sKU1).isNotEqualTo(sKU2);
        sKU1.setId(null);
        assertThat(sKU1).isNotEqualTo(sKU2);
    }
}
