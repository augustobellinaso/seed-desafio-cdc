package com.augustobellinaso.casadocodigo.cadastrolivro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LivrosController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/livros")
    @Transactional
    public String cria(@RequestBody @Valid NovoLivroRequest request) {
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }

    @GetMapping(value = "/livros")
    public List<?> lista() {
        List<?> livros = manager.createQuery("SELECT l.id, l.titulo FROM Livro l").getResultList();

        Assert.isTrue(!livros.isEmpty(), "Nenhum livro encontrado");

        return livros;
    }
}
