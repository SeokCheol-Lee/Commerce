package com.example.domain.domain.repository;

import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.model.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> searchByName(String name) {
        String search = "%"+name+"%";

        //search의 이름과 같은 product의 name을 조회
        QProduct product = QProduct.product;
        return queryFactory.selectFrom(product)
            .where(product.name.like(search))
            .fetch();
    }
}
