package org.turkcell.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turkcell.ecommerce.business.abstracs.ProductService;
import org.turkcell.ecommerce.entities.Product;
import org.turkcell.ecommerce.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    @Autowired
    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfBrandExist(id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        validateProduct(product);
        product.setId(id);
        return repository.save(product);
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
