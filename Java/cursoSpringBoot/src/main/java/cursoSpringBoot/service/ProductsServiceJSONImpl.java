package cursoSpringBoot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cursoSpringBoot.domain.Product;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Lazy
@Primary
@Service("jsonResourceService")
public class ProductsServiceJSONImpl implements ProductService{

    public ProductsServiceJSONImpl(){
        System.out.println("Mamahuevo");
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products;

        try{
            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {});

            return products;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
