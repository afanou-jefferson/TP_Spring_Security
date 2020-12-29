package fr.stazi.epsi.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.repository.UserRepository;

@Service
public class SecurityMethodsService {

	@Autowired
	private UserRepository userRepository;
	
	//UserDetails équivaut à User pour SpringSecurity
	public boolean canManage( UserDetails userConnecte, Long idCell ) {
		
		User user = userRepository.findByUsername(userConnecte.getUsername()).orElseThrow(() -> new UsernameNotFoundException(userConnecte.getUsername()));
		
		for (Cell cell : user.getCells()) {
			if (cell.getId().equals(idCell)) {
				return true;
			}
		}
		
		return false;
	}
	
}
