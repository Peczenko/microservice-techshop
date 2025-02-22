package project.org.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.org.techshop.enitity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(String customerId);
}
