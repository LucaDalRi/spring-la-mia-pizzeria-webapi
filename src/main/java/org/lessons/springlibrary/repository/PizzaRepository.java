package org.lessons.springlibrary.repository;

import org.lessons.springlibrary.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
