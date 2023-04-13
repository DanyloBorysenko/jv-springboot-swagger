package mate.academy.springboot.swagger.service;

import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product by id " + id + " doesn't exist"));
    }

    @Override
    public Product update(Product product) {
        Long id = product.getId();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return productRepository.save(product);
        }
        throw new NoSuchElementException("Product by id " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
