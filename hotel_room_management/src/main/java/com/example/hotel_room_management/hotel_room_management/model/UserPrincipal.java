package com.example.hotel_room_management.hotel_room_management.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
    private User user;
    
    public UserPrincipal(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Role roles=user.getRole();
		List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roles.getName()));
		return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getEmail();
    }

}
