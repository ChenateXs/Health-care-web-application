package it.engineering.nc.app.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.engineering.nc.app.entity.UserEntity;
import it.engineering.nc.app.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> user = userRepository.findByUsername(username);
		user
		.orElseThrow(()->new UsernameNotFoundException("User " + username+ " does not exist!"));
		
		System.out.println("user: "+ username+ " logged in.");
		return new MyUserDetails( user.get().getUsername(), user.get().getPassword());
	}

}
