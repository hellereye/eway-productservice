package com.mycloud.product.service.mapper;


import com.mycloud.product.domain.*;
import com.mycloud.product.service.dto.SKUDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SKU} and its DTO {@link SKUDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SKUMapper extends EntityMapper<SKUDTO, SKU> {



    default SKU fromId(Long id) {
        if (id == null) {
            return null;
        }
        SKU sKU = new SKU();
        sKU.setId(id);
        return sKU;
    }
}
