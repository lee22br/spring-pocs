package com.example.data;

import com.example.data.domain.CustomerOrder;
import com.example.data.domain.OrderItem;
import com.example.data.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootApplication
public class SpringDataJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(OrderRepository repository) {
        return args -> {
            System.out.println("--- 1. Creating a New Order ---");
            Set<OrderItem> items = Set.of(
                    new OrderItem(null, "Mechanical Keyboard", 1, new BigDecimal("120.00")),
                    new OrderItem(null, "Wireless Mouse", 2, new BigDecimal("45.00"))
            );

            CustomerOrder newOrder = new CustomerOrder(null, "Alice", "PENDING", items);

            CustomerOrder savedOrder = repository.save(newOrder);
            System.out.println("Saved Order ID: " + savedOrder.id());


            System.out.println("\n--- 2. Fetching by Custom Query ---");
            repository.findByStatus("PENDING").forEach(order -> {
                System.out.println("Found Order for: " + order.customerName());
                System.out.println("Items in order: " + order.items().size());
            });


            System.out.println("\n--- 3. Updating an Aggregate ---");
            CustomerOrder updatedOrder = savedOrder.withStatus("SHIPPED");

            repository.save(updatedOrder);
            System.out.println("Order status updated to: " + repository.findById(savedOrder.id()).get().status());


            System.out.println("\n--- 4. Deleting an Aggregate ---");
            repository.deleteById(savedOrder.id());
            System.out.println("Order count after deletion: " + repository.count());
        };
    }
}
