import entity.Cart;
import entity.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ApplicationContext.class);

        boolean exit = false;
        Cart cart = null;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер команды: \n 1 - Начать новые покупки; \n 2 - Добавить товар \n 3 - Вывести список товаров в корзине \n 4 - Закончить покупки");
            String command = scanner.next();

            switch (command) {
                case "1": {
                    cart = context.getBean(Cart.class);
                    System.out.println("Корзина продуктов создана, введите новую команду");
                    continue;
                }
                case "2": {
                    if (cart == null) {
                        System.out.println("Сперва создайте корзину");
                    } else {
                        System.out.println("Введите название продукта");
                        String name = scanner.next();
                        System.out.println("Введите цену продукта");
                        Integer price = Integer.valueOf(scanner.next());
                        Product product1 = context.getBean(Product.class);
                        product1.setName(name);
                        product1.setPrice(price);
                        cart.getProductRepository().saveProduct(product1);
                    }
                    continue;
                }
                case "3": {
                    if (cart == null) {
                        System.out.println("Сперва создайте корзину");
                    } else {
                        List<Product> products = cart.getProductRepository().getProducts();
                        for (Product product : products) {
                            System.out.println(product.getName());
                        }
                    }
                    continue;
                }
                case "4": {
                    exit = true;
                }
            }
        } while (!exit);

    }
}