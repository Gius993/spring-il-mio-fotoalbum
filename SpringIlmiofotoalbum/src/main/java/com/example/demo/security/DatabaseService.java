package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.Userrepo;


@Service
public class DatabaseService implements UserDetailsService{
	@Autowired
	private Userrepo userRepo;
	
	@Override
    public UserDetails loadUserByUsername(String username) 
    	throws UsernameNotFoundException {
      Optional<User> user = userRepo.findByUsername(username);
      if(user.isPresent()) {
        return new DatabaseUserDatails(user.get());
      } else throw new UsernameNotFoundException("Username not found");
    }
}
