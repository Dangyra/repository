package lt.ku.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.library.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
