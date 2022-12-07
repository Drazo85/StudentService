package com.radomir.drazic.radomirdrazicBE.service.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.entity.User;
import com.radomir.drazic.radomirdrazicBE.repository.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        user.orElseThrow(() -> new UsernameNotFoundException("user " + username + " doesn't exist!"));
        return new MyUserDetails(user.get().getUsername(), user.get().getPassword(), user.get().getFirstName(), user.get().getLastName(), user.get().getAuthoritiesList());
    }
}
