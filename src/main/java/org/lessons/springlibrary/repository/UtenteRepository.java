package org.lessons.springlibrary.repository;

import org.lessons.springlibrary.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
