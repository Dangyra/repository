package lt.ku.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.ku.library.entities.Client;
import lt.ku.library.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public Client addClient(Client client) {
		return clientRepository.save(client);
	}
	
	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	public Client updateClient(Client client) {
		Client old=clientRepository.getById(client.getId());
		old.setName(client.getName());
		old.setSurname(client.getSurname());
		old.setEmail(client.getEmail());
		old.setPhone(client.getPhone());
		old.setBook(client.getBook());
		clientRepository.save(old);
		return old;
	}
	
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
	
	public Client getClient(Integer id) {
		return clientRepository.getById(id);
	}
}
