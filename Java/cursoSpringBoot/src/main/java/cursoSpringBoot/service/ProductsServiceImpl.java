package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("listResourceService")
public class ProductsServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", 799.99, 10),
            new Product(2, "Smartphone", 499.99, 25),
            new Product(3, "Tablet", 299.99, 30),
            new Product(4, "Smartwatch", 199.99, 30)
    ));

    @Override
    public List<Product> getProducts(){
        return products;
    }
}