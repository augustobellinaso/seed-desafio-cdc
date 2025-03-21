package com.augustobellinaso.casadocodigo.finalizacompra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinalizaCompraController {

    @Autowired
    private EstadoPaisValidator estadoPaisValidator;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CupomRepository cupomRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPaisValidator);
    }

    @PostMapping(value = "/compras")
    @Transactional
    public String cria(@RequestBody @Valid NovaCompraRequest request) {
        Compra novaCompra = request.toModel(manager, cupomRepository);
        manager.persist(novaCompra);

        return novaCompra.toString();
    }
}
