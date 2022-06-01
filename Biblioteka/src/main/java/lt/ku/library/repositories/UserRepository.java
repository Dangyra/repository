package lt.ku.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.library.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

}

