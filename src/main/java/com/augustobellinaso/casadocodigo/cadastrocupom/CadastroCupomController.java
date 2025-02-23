package com.augustobellinaso.casadocodigo.cadastrocupom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastroCupomController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/cupons")
    @Transactional
    public String cria(NovoCupomRequest request) {
        Cupom novoCupom = request.toModel();
        manager.persist(novoCupom);
        return novoCupom.toString();
    }
}
