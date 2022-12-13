package com.cognixia.jump.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.jump.model.User;

//UserDetails --> classes that are of this type are used by spring security to find all of the necessary information
//for authorization and authentication
public class MyUserDetails implements UserDetails {

private static final long serialVersionUID = 1L;

private String username;
private String password;
private boolean enabled;
private List<GrantedAuthority> authorities;

public MyUserDetails( User user ) {

this.username = user.getEmail();
this.password = user.getPassword();
this.enabled = user.isEnabled();

// Granted Authorities -> permissions/grants a user will have that allows to access different things (APIs)
// GA is given based on the Roles a user has
this.authorities = Arrays.asList( new SimpleGrantedAuthority( user.getRole().name() ) );
}



@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
return authorities;
}

@Override
public String getPassword() {
return password;
}
@Override
public String getUsername() {
return username;
}

// all methods after here:
// - DON'T NEED to store this type of info within a user model/table
// - store this info if its worthwhile for your security
// - have all these methods return true manually and don't store this info in a table

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
return enabled;
}

}
