package com.fu.prenancytracker.security;

import com.fu.prenancytracker.model.User;
import com.fu.prenancytracker.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }

        if (!user.getEmailVerified()) {
            throw new AccessDeniedException("Your Account Requires Verification");
        }

        return new CustomUserDetails(user);
    }
}
