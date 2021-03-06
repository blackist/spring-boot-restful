package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.model.security.SecurityModelFactory;
import org.blackist.web.springbootor.repository.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(system.getRole().getName()));
//        System.err.println("username: " + s + ", roel: " + system.getRole().getName());
//
//        return new org.springframework.security.core.userdetails.User(
//                system.getUsername(),
//                system.getPassword(),
//                authorities);
        return SecurityModelFactory.create(user);
    }
}
