package com.mycloud.product.repository;

import com.mycloud.product.domain.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @NotNull
    @Override
    @EntityGraph(attributePaths = "children")
    List<Category> findAll();

    //attributePaths层级是固定的，层级的数量决定了left join的次数
    @Override
    //@EntityGraph(attributePaths = "children.children.children.children")
    @EntityGraph(value = "Category.findByParent")
    Optional<Category> findById(Long aLong);

    //LOAD("javax.persistence.loadgraph")：当javax.persistence.loadgraph属性用于指定实体图时，
    // 由实体图的attributePaths指定的属性将被视为FetchType.EAGER，未指定的属性，将根据其设置的或默认的FetchType来进行处理。
    //FETCH("javax.persistence.fetchgraph")：当javax.persistence.fetchgraph属性用于指定实体图时，
    // 由实体图的attributePaths指定的属性将被视为FetchType.EAGER，而未指定的属性被视为FetchType.LAZY。
//    @Override
    //@EntityGraph(attributePaths = "children",type = EntityGraph.EntityGraphType.FETCH)
//    Page<Category> findAll(Pageable pageable);

    /**
     * 查询根据父节点查询门类
     * @param category category
     * @return list
     */
//    @EntityGraph(value = "Category.findByParent")
    @EntityGraph(attributePaths = {"children.children.children"})
    List<Category> findByParent(Category category);

}
