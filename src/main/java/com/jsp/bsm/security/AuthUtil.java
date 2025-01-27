package com.jsp.bsm.security;

import com.jsp.bsm.entity.User;
import com.jsp.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component // because current class should be bean
@AllArgsConstructor
public class AuthUtil {

    private final UserRepository userRepository;

    public String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser(){
        return userRepository.findByEmail(this.getCurrentUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Failed to authenticate"));
    }
}