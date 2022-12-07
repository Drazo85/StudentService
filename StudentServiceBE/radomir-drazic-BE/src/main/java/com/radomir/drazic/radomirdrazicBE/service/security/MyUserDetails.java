package com.radomir.drazic.radomirdrazicBE.service.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.radomir.drazic.radomirdrazicBE.entity.Authorities;

public class MyUserDetails implements UserDetails {
    
	private static final long serialVersionUID = -4878525399689671080L;
	
	private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;
    
    private List<GrantedAuthority> authorities;

    public MyUserDetails(String username, String password, String firstname, String lastname, List<Authorities> authoritiesList) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.authorities = authoritiesList.stream()
                .map((Authorities role) -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    

    public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	
	@JsonIgnore
	@Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
