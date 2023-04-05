package org.turkcell.ecommerce.business.concretes;

import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turkcell.ecommerce.business.abstracs.ProductService;
import org.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import org.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import org.turkcell.ecommerce.business.dto.response.create.CreateProductResponse;
import org.turkcell.ecommerce.business.dto.response.get.GetAllProductsResponse;
import org.turkcell.ecommerce.business.dto.response.get.GetProductResponse;
import org.turkcell.ecommerce.business.dto.response.update.UpdateProductResponse;
import org.turkcell.ecommerce.entities.Product;
import org.turkcell.ecommerce.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    @Autowired
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = repository.findAll();
        List<GetAllProductsResponse> responses = products
                .stream()
                .map(product -> mapper.map(product,GetAllProductsResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetProductResponse getById(int id) {
        checkIfBrandExist(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = mapper.map(product,GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request,Product.class);
        product.setId(0);
        validateProduct(product);
        Product createProduct = repository.save(product);
        CreateProductResponse response = mapper.map(createProduct, CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(id);
        validateProduct(product);
        repository.save(product);
        UpdateProductResponse response = mapper.map(product, UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    //! Business rules
    private void validateProduct(Product product) {
        checkIfPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfPriceValid(Product product) {
        if (product.getPrice() <= 0) throw new IllegalArgumentException("Price cannot be less than or equal to zero.");

    }

    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero.");
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50)
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters.");
    }
    private void checkIfBrandExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Marka bulunamadi!");
    }

}
