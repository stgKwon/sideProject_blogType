package com.blogtype.sideproject.util.security;


import com.blogtype.sideproject.model.user.User;
import com.blogtype.sideproject.repository.user.UserRepository;
import com.blogtype.sideproject.util.constants.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsImpl loadUserByUsername(String userPk) throws UsernameNotFoundException {
    User user = userRepository.findById(Long.parseLong(userPk))
            .orElseThrow(() -> new UsernameNotFoundException(MessageConstant.INVALID_USER_ID));

    return new UserDetailsImpl(user);
  }
}