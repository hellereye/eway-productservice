package com.mycloud.product.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class SpecificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specification.class);
        Specification specification1 = new Specification();
        specification1.setId(1L);
        Specification specification2 = new Specification();
        specification2.setId(specification1.getId());
        assertThat(specification1).isEqualTo(specification2);
        specification2.setId(2L);
        assertThat(specification1).isNotEqualTo(specification2);
        specification1.setId(null);
        assertThat(specification1).isNotEqualTo(specification2);
    }
}
