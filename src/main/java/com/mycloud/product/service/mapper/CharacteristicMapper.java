package com.mycloud.product.service.mapper;


import com.mycloud.product.domain.*;
import com.mycloud.product.service.dto.CharacteristicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Characteristic} and its DTO {@link CharacteristicDTO}.
 */
@Mapper(componentModel = "spring", uses = {SpecificationMapper.class})
public interface CharacteristicMapper extends EntityMapper<CharacteristicDTO, Characteristic> {

    @Mapping(source = "specification.id", target = "specificationId")
    CharacteristicDTO toDto(Characteristic characteristic);

    @Mapping(target = "values", ignore = true)
    @Mapping(target = "removeValues", ignore = true)
    @Mapping(source = "specificationId", target = "specification")
    Characteristic toEntity(CharacteristicDTO characteristicDTO);

    default Characteristic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Characteristic characteristic = new Characteristic();
        characteristic.setId(id);
        return characteristic;
    }
}
