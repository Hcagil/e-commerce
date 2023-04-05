package org.turkcell.ecommerce.business.abstracs;

import org.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import org.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import org.turkcell.ecommerce.business.dto.response.create.CreateProductResponse;
import org.turkcell.ecommerce.business.dto.response.get.GetAllProductsResponse;
import org.turkcell.ecommerce.business.dto.response.get.GetProductResponse;
import org.turkcell.ecommerce.business.dto.response.update.UpdateProductResponse;
import org.turkcell.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(int id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(int id, UpdateProductRequest request);
    void delete(int id);
}
