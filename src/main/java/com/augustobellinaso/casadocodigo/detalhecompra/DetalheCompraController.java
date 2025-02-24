package com.augustobellinaso.casadocodigo.detalhecompra;

import com.augustobellinaso.casadocodigo.finalizacompra.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheCompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/compras/{id}")
    public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {

        Compra compraBuscada = entityManager.find(Compra.class, id);

        if (compraBuscada == null) {
            return ResponseEntity.notFound().build();
        }

        DetalheCompraResponse detalheResponse = new DetalheCompraResponse(compraBuscada);
        return ResponseEntity.ok(detalheResponse);

    }
}
