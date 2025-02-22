package com.augustobellinaso.casadocodigo.detalhelivro;

import com.augustobellinaso.casadocodigo.cadastrolivro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheLivroSiteController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {

        Livro livroBuscado = entityManager.find(Livro.class, id);

        if (livroBuscado == null) {
            return ResponseEntity.notFound().build();
        }

        DetalheSiteLivroResponse detalheResponse = new DetalheSiteLivroResponse(livroBuscado);
        return ResponseEntity.ok(detalheResponse);
    }
}
