package com.mycloud.product.service.mapper;


import com.mycloud.product.domain.*;
import com.mycloud.product.service.dto.CharacteristicValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CharacteristicValue} and its DTO {@link CharacteristicValueDTO}.
 */
@Mapper(componentModel = "spring", uses = {CharacteristicMapper.class})
public interface CharacteristicValueMapper extends EntityMapper<CharacteristicValueDTO, CharacteristicValue> {

    @Mapping(source = "characteristic.id", target = "characteristicId")
    CharacteristicValueDTO toDto(CharacteristicValue characteristicValue);

    @Mapping(source = "characteristicId", target = "characteristic")
    CharacteristicValue toEntity(CharacteristicValueDTO characteristicValueDTO);

    default CharacteristicValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        CharacteristicValue characteristicValue = new CharacteristicValue();
        characteristicValue.setId(id);
        return characteristicValue;
    }
}
