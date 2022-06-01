package lt.ku.library.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.library.entities.Client;
import lt.ku.library.services.BookService;
import lt.ku.library.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	BookService bookService;
	/*
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("clients", clientService.getClients());
		return "client_list";
	}
	
	@GetMapping("/new")
	public String clientNew(Model model) {
		return "client_new";
	}
	
	@PostMapping("/new")
	public String addClient(
			@Valid
			BindingResult result,
			@RequestParam("name") String name,
			@RequestParam("surname") String surname,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone{
		if (result.hasErrors()) {
			return "client_new";
		}
		Client c = new Client(name, surname, email, phone);
		clientService.addClient(c);
		return "redirect:/client/";
	}

	@PostMapping("/new")
	public String addClient(
			Model model,
			@Valid
			BindingResult result,
			@ModelAttribute Client client) {
		if (result.hasErrors()) {
			return "client_new";
		}
		clientService.addClient(client);
		model.addAttribute("client", clientService.getClients());
		return "redirect:/client/";
	}
	
	@PostMapping("/new")
	public String storeClient(
			@Valid
			@ModelAttribute Client client, 
			BindingResult result,
			
			@RequestParam("bookId")Integer bookId,
			
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("books", bookService.getBooks());
			return "client_new";
		}
		client.setBook(bookService.getBook(bookId));
		clientService.addClient(client);
		
		return "redirect:/client/";
		
	}
	*/
	
	@GetMapping("/")
	public String getClients(Model model) {
		model.addAttribute("clients",clientService.getClients());
		return "client_list";
	}
	
	@GetMapping("/new")
	public String newClient(Model model) {
		model.addAttribute("client", new Client());
		model.addAttribute("books", bookService.getBooks());
		return "client_new";
	}
	
	@PostMapping("/new")
	public String storeClient(
			@Valid
			@ModelAttribute 
			Client client, 
			BindingResult result,
			
			@RequestParam("bookId")
			Integer bookId,
			
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("books", bookService.getBooks());
			return "client_new";
		}
		client.setBook(bookService.getBook(bookId));
		clientService.addClient(client);
		
		return "redirect:/client/";
		
	}
	
	@GetMapping("/update/{id}")
	public String clientNew(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("client", clientService.getClient(id));
		model.addAttribute("books", bookService.getBooks());
		return "client_update";
	}
	
	@PostMapping("/update/{id}")
	public String clientUpdate(@PathVariable("id") Integer id, @ModelAttribute Client c) {
		clientService.updateClient(c);
		return "redirect:/client/";
	}
	
	@GetMapping("/delete/{id}")
	public String clientDelete( @PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return "redirect:/client/";
	}

}
