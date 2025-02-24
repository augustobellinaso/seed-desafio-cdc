package com.augustobellinaso.casadocodigo.compartilhado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ListaTudo {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value = "/lista-tudo")
    public HashMap<String, Object> listar() {
        HashMap<String, Object> resultado = new HashMap<>();
        List<?> autores = entityManager.createQuery("SELECT a FROM Autor a").getResultList();

        resultado.put("autores", autores.toString());

        List<?> categorias = entityManager.createQuery("SELECT c FROM Categoria c").getResultList();
        resultado.put("categorias", categorias.toString());

        List<?> cupons = entityManager.createQuery("SELECT c FROM Cupom c").getResultList();
        resultado.put("cupons", cupons);
        return resultado;
    }
}
