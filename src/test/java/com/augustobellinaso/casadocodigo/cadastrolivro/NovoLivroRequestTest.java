package com.augustobellinaso.casadocodigo.cadastrolivro;

import com.augustobellinaso.casadocodigo.cadastrocategoria.Categoria;
import com.augustobellinaso.casadocodigo.novoautor.Autor;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

class NovoLivroRequestTest {

    NovoLivroRequest request = new NovoLivroRequest(1L, 1L, LocalDate.now().plusDays(1L),
            "", 100, BigDecimal.TEN, "", "", "");

    @Test
    @DisplayName("cria o livro com autor e categoria cadastrados")
    void teste1() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1L))
                .thenReturn(new Categoria("Categoria1"));

        Mockito.when(manager.find(Autor.class, 1L))
                .thenReturn(new Autor("Nome do autor", "email@email.com", "Autor teste"));

        Assertions.assertNotNull(request.toModel(manager));
    }

    @Test
    @DisplayName("nao cria o livro caso o autor nao esteja cadastrado")
    void teste2() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1L))
                .thenReturn(new Categoria("Categoria1"));

        Mockito.when(manager.find(Autor.class, 1L))
                .thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            request.toModel(manager);
        });
    }

    @Test
    @DisplayName("nao cria o livro caso a categoria autor nao esteja cadastrado")
    void teste3() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1L))
                .thenReturn(null);

        Mockito.when(manager.find(Autor.class, 1L))
                .thenReturn(new Autor("Nome", "mail@mail.com", "descrição"));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            request.toModel(manager);
        });
    }


}
