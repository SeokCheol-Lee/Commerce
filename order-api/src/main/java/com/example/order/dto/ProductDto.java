package com.example.order.dto;

import com.example.domain.domain.model.product.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductItemDto> items;

    public static ProductDto from(Product product){
        List<ProductItemDto> items = product.getProductItems()
            .stream().map(ProductItemDto::from).collect(Collectors.toList());

        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .items(items)
            .build();
    }

    public static ProductDto witoutItemsFrom(Product product){

        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .build();
    }
}
