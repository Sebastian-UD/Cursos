package cursoSpringBoot.controllers;

import cursoSpringBoot.configurations.ExternalizedConfigurations;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productsService;
    private final ExternalizedConfigurations externalizedConfigurations;

    public ProductController(@Lazy @Qualifier("jsonResourceService") ProductService productsService,
                             ExternalizedConfigurations externalizedConfigurations) {
        this.productsService = productsService;
        this.externalizedConfigurations = externalizedConfigurations;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){

        System.out.println(externalizedConfigurations.toString());

        List<Product> list = productsService.getProducts();
        return ResponseEntity.ok(list);
    }

}
