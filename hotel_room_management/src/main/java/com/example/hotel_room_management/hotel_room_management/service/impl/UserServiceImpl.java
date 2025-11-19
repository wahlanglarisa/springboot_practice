package com.example.hotel_room_management.hotel_room_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.model.UserPrincipal;
import com.example.hotel_room_management.hotel_room_management.repository.RoleRepository;
import com.example.hotel_room_management.hotel_room_management.repository.UserRepository;
import com.example.hotel_room_management.hotel_room_management.service.UserService;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    	@Lazy

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user=userRepository.findByEmail(username);
        return new UserPrincipal(user);
    }
    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        user.setRole(roleRepository.findByName("Guest"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
