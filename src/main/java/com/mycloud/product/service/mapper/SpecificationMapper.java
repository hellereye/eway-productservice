package com.mycloud.product.service.mapper;


import com.mycloud.product.domain.*;
import com.mycloud.product.service.dto.SpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specification} and its DTO {@link SpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SpecificationMapper extends EntityMapper<SpecificationDTO, Specification> {


    @Mapping(target = "characteristices", ignore = true)
    @Mapping(target = "removeCharacteristices", ignore = true)
    Specification toEntity(SpecificationDTO specificationDTO);

    default Specification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Specification specification = new Specification();
        specification.setId(id);
        return specification;
    }
}
