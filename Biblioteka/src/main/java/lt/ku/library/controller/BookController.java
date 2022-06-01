package lt.ku.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lt.ku.library.entities.Book;
import lt.ku.library.services.BookService;
import lt.ku.library.services.FileStorageService;


@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	@Autowired
	FileStorageService fileStorage;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("books", bookService.getBooks());
		return "book_list";
	}
	
	@GetMapping("/new")
	public String bookNew(Model model) {
		model.addAttribute("book", new Book());
		return "book_new";
	}
	
	@PostMapping("/new")
	public String addBook(
			@RequestParam("name") String name,
			@RequestParam("author") String author,
			@RequestParam("genre") String genre,
			@RequestParam("publication_details") String publication_details,
			@RequestParam("description") MultipartFile description,
			@RequestParam("places") Integer places){
		Book b = new Book(name, author, genre, publication_details, description.getOriginalFilename(),places);
		bookService.addBook(b);
		fileStorage.store(description,b.getId().toString());
		return "redirect:/book/";
	}
	
	@GetMapping("/update/{id}")
	public String bookNew(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("book", bookService.getBook(id));
		return "book_update";
	}
	
	@PostMapping("/update/{id}")
	public String bookUpdate(@PathVariable("id") Integer id, @ModelAttribute Book b) {
		bookService.updateBook(b);
		return "redirect:/book/";
	}
	
	@GetMapping("/delete/{id}")
	public String bookDelete( @PathVariable("id") Integer id) {
		bookService.deleteBook(id);
		return "redirect:/book/";
	}
	
	@GetMapping("/description/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getDescription(@PathVariable Integer id) {
		Resource file=fileStorage.loadFile(id.toString());
		Book b=bookService.getBook(id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "description; filename=\""+b.getFileName()+"\"")
				.body(file);
	}
}
