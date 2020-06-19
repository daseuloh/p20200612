package com.example.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.User1Mapper;
import com.example.vo.User1VO;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private User1Mapper user1Mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username을 이용하여 mapper에서 원하는 값을 받아옴.
		User1VO obj = user1Mapper.findByUsername(username);
		
		String[] userRoles = { obj.getRole() };
		
		Collection<GrantedAuthority> roles 
			= AuthorityUtils.createAuthorityList(userRoles);
		
		
		return new User(obj.getUsername(), obj.getPassword(), roles);
	}

}