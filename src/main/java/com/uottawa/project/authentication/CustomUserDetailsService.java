package com.uottawa.project.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private MainUsersRepository mainUsersRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MainUsers user = mainUsersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
        return new CustomUserDetails(user);
    }
 
}