package com.augustobellinaso.casadocodigo.novoautor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoresController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "/autores")
    @Transactional
    public String cria(@RequestBody @Valid NovoAutorRequest request) {
        Autor autor = request.toModel();
        entityManager.persist(autor);

        return autor.toString();
    }
}
