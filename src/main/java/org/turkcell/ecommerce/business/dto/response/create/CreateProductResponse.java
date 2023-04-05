package org.turkcell.ecommerce.business.dto.response.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
}
