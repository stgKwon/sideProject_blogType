package com.blogtype.sideproject.util.security;


import com.blogtype.sideproject.model.user.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

  private final User user;

  public UserDetailsImpl(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }
  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }


  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}