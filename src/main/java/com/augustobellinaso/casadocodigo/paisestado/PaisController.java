package com.augustobellinaso.casadocodigo.paisestado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "/paises")
    @Transactional
    public String cria(@RequestBody @Valid NovoPaisRequest request) {
        Pais novoPais = new Pais(request.getNome());
        entityManager.persist(novoPais);
        return novoPais.toString();
    }
}
