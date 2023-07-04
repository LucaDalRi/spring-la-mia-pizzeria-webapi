package org.lessons.springlibrary.service;

import org.lessons.springlibrary.model.Pizza;
import org.lessons.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public List<Pizza> getAll(Optional<String> keywordOpt) {
        if (keywordOpt.isEmpty()) {
            return pizzaRepository.findAll();
        } else {
            return pizzaRepository.findByNome(keywordOpt.get());
        }
    }

    public Pizza getById(Integer id) throws ChangeSetPersister.NotFoundException {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isPresent()) {
            return pizzaOpt.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public Pizza create(Pizza pizza) throws IllegalArgumentException {
        if (!isUniqueId(pizza)) {
            throw new IllegalArgumentException(String.valueOf(pizza.getId()));
        }
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setId(pizza.getId());
        pizzaToPersist.setNome(pizza.getNome());
        pizzaToPersist.setDescrizione(pizza.getDescrizione());
        pizzaToPersist.setUrlFoto(pizza.getUrlFoto());
        pizzaToPersist.setPrezzo(pizza.getPrezzo());
        return pizzaRepository.save(pizzaToPersist);
    }

    private boolean isUniqueId(Pizza formPizza) {
        Optional<Pizza> result = pizzaRepository.findById(formPizza.getId());
        return result.isEmpty();

    }
}
