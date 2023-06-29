package com.eazybytes.config;

import com.eazybytes.model.Authority;
import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EazybankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public EazybankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for user  " + username);
        } else {
            userName = customers.get(0).getEmail();
            password = customers.get(0).getPwd();
            System.out.println(authorityList(customers.get(0).getAuthority()));
        }
        return new User(username, password,authorityList(customers.get(0).getAuthority()));
    }


    private List<GrantedAuthority> authorityList(Set<Authority> authoritySet){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Authority authority : authoritySet){
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authorities;

    }


}
