package com.augustobellinaso.casadocodigo.cadastrocategoria;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriasController {

    private final EntityManager manager;

    public CategoriasController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping(value = "/categorias")
    @Transactional
    public String cria(@RequestBody @Valid NovaCategoriaRequest request) {

        Categoria categoria = new Categoria(request.getNome());
        manager.persist(categoria);
        return categoria.toString();
    }
}
