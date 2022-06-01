package lt.ku.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.library.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
