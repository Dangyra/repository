package lt.ku.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.library.entities.Order;
import lt.ku.library.services.BookService;
import lt.ku.library.services.ClientService;
import lt.ku.library.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/")
	public String getOrder(Model model) {
		model.addAttribute("orders", orderService.getOrders());
		return "order_list";
	}
	
	@GetMapping("/new")
	public String newOrder(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("books", bookService.getBooks());
		return "order_new";
	}
	
	@PostMapping("/new")
	public String storeOrder(
			@ModelAttribute Order order,
			@RequestParam("clientID") Integer clientID,
			@RequestParam("bookID") Integer bookID,
			Model model) {
		
		order.setClient(clientService.getClient(clientID));
		order.setBook(bookService.getBook(bookID));
		orderService.addOrder(order);
		
		return "redirect:/order/";
	}
	
	@GetMapping("/update/{id}")
	public String ordernNew(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.getOrder(id));
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("books", bookService.getBooks());
		return "order_update";
	}
	
	@PostMapping("/update/{id}")
	public String orderUpdate(@PathVariable("id") Integer id, @ModelAttribute Order o) {
		orderService.updateOrder(o);
		return "redirect:/order/";
	}
	
	@GetMapping("/delete/{id}")
	public String orderDelete( @PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return "redirect:/order/";
	}

}
