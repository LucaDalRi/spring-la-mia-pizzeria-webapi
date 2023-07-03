package org.lessons.springlibrary.security;

import org.lessons.springlibrary.model.Utente;
import org.lessons.springlibrary.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> result = utenteRepository.findByEmail(username);
        if (result.isPresent()) {
            return new DatabaseUserDetails(result.get());
        } else {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
    }
}
