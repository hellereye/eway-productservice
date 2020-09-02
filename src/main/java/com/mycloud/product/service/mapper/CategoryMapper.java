package com.mycloud.product.service.mapper;


import com.mycloud.product.domain.*;
import com.mycloud.product.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "parent.id", target = "parentId")
    CategoryDTO toDto(Category category);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProducts", ignore = true)
    //@Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
