package lt.ku.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
