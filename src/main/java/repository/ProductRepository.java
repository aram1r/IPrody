package repository;

import entity.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ProductRepository {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "Морковь", 50));
        products.add(new Product(2, "Свекла", 100));
        products.add(new Product(3, "Картофель", 50));
        products.add(new Product(4, "Лук", 100));
        products.add(new Product(5, "Сыр", 50));
        products.add(new Product(6, "Колбаса", 100));
    }


    public void setProducts(List<Product> products) {this.products = products;}

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public ProductRepository() {}

    public void saveProduct(Product product) {
        products.add(product);
    };

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
