package com.backend.blog.security;

import com.backend.blog.models.User;
import com.backend.blog.repo.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepo.findByEmail(email)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));
         return new BlogUserDetails(user);
    }
}
