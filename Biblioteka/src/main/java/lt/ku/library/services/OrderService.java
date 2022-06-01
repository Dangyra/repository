package lt.ku.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.library.entities.Order;
import lt.ku.library.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	
	public Order getOrder(Integer id) {
		return orderRepository.getById(id);
	}
	
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Order order) {
		Order old=this.getOrder(order.getId());
		old.setClient(order.getClient());
		old.setBook(order.getBook());
		old.setOrder_date(order.getOrder_date());
		orderRepository.save(old);
		return old;
	}
	
	public void deleteOrder(Integer id) {
		orderRepository.deleteById(id);
	}
}
