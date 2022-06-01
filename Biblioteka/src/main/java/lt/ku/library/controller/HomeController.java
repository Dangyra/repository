package lt.ku.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lt.ku.library.entities.User;
import lt.ku.library.services.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		
		//User user=new User("dangyra","biblioteka","dangyra.tadaraite@gmail.com","admin");
		//userService.addUser(user);
		
		return "home";
	}
}
