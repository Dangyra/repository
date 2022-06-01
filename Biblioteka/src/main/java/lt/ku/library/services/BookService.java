package lt.ku.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.library.entities.Book;
import lt.ku.library.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBook(Integer id) {
		return bookRepository.getById(id);
	}
	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Book updateBook(Book book) {
		Book old=bookRepository.getById(book.getId());
		old.setName(book.getName());
		old.setAuthor(book.getAuthor());
		old.setGenre(book.getGenre());
		old.setPublication_details(book.getPublication_details());
		old.setPlaces(book.getPlaces());
		bookRepository.save(old);
		return old;
	}
	
	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
	}
}
