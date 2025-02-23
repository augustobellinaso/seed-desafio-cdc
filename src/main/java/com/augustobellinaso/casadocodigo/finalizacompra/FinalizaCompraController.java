package com.augustobellinaso.casadocodigo.finalizacompra;

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

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPaisValidator);
    }

    @PostMapping(value = "/compras")
    public String cria(@RequestBody @Valid NovaCompraRequest request) {
        return request.toString();
    }
}
