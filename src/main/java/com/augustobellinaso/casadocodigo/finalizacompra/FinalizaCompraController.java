package com.augustobellinaso.casadocodigo.finalizacompra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPaisValidator);
    }

    @PostMapping(value = "/compras")
    public String cria(@RequestBody @Valid NovaCompraRequest request) {
        Compra novaCompra = request.toModel(manager);
        return novaCompra.toString();
    }
}
