package com.mycloud.product.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycloud.product.web.rest.TestUtil;

public class SpecificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecificationDTO.class);
        SpecificationDTO specificationDTO1 = new SpecificationDTO();
        specificationDTO1.setId(1L);
        SpecificationDTO specificationDTO2 = new SpecificationDTO();
        assertThat(specificationDTO1).isNotEqualTo(specificationDTO2);
        specificationDTO2.setId(specificationDTO1.getId());
        assertThat(specificationDTO1).isEqualTo(specificationDTO2);
        specificationDTO2.setId(2L);
        assertThat(specificationDTO1).isNotEqualTo(specificationDTO2);
        specificationDTO1.setId(null);
        assertThat(specificationDTO1).isNotEqualTo(specificationDTO2);
    }
}
