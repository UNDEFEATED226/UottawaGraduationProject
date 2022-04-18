package com.uottawa.project.authentication;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.uottawa.project.entity.MainMembers;
import com.uottawa.project.repository.MainMembersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MainMembersRepository mainMembersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MainMembers member = mainMembersRepository.findByEmail(username);
		if (member == null) {
			throw new UsernameNotFoundException("USER NOT FOUND");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+member.getRole()));
		return new CustomUserDetails(member, grantedAuthorities);
	}

}