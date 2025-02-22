package com.augustobellinaso.casadocodigo.paisestado;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping(value = "/estados")
    @Transactional
    public String cria(@RequestBody @Valid NovoEstadoRequest request) {
        Estado novoEstado = request.toModel(entityManager);
        entityManager.persist(novoEstado);
        return novoEstado.toString();

    }
}
