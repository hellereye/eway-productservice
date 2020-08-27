package com.mycloud.product.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class SKUDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SKUDTO.class);
        SKUDTO sKUDTO1 = new SKUDTO();
        sKUDTO1.setId(1L);
        SKUDTO sKUDTO2 = new SKUDTO();
        assertThat(sKUDTO1).isNotEqualTo(sKUDTO2);
        sKUDTO2.setId(sKUDTO1.getId());
        assertThat(sKUDTO1).isEqualTo(sKUDTO2);
        sKUDTO2.setId(2L);
        assertThat(sKUDTO1).isNotEqualTo(sKUDTO2);
        sKUDTO1.setId(null);
        assertThat(sKUDTO1).isNotEqualTo(sKUDTO2);
    }
}
