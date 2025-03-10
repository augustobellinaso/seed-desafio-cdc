package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocategoria.Categoria;
import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import com.augustobellinaso.casadocodigo.cadastrolivro.Livro;
import com.augustobellinaso.casadocodigo.novoautor.Autor;
import com.augustobellinaso.casadocodigo.paisestado.Estado;
import com.augustobellinaso.casadocodigo.paisestado.Pais;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class NovaCompraRequestTest {

    private EntityManager manager = Mockito.mock(EntityManager.class);
    private CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
    private List<NovoPedidoItemRequest> itens = List
            .of(new NovoPedidoItemRequest(1l, 5));
    private NovoPedidoRequest pedido = new NovoPedidoRequest(itens, new BigDecimal("50"));
    private Pais pais = new Pais("pais");
    private Autor autor = new Autor("nome", "email@email.com", "descricao");
    private Categoria categoria = new Categoria("categoria");
    private Livro livro = new Livro("titulo", "resumo", "Sumario", BigDecimal.TEN, 100, "987864564564",
            LocalDate.now().plusDays(5L), autor, categoria);

    {
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais);
        Mockito.when(manager.find(Livro.class, 1L)).thenReturn(livro);
        Mockito.when(manager.find(Estado.class, 1L)).thenReturn(new Estado("estado", pais));
        Mockito.when(cupomRepository.getByCodigo("codigo-cupom"))
                .thenReturn(new Cupom("codigo-cupom", BigDecimal.TEN,LocalDate.now().plusDays(1L)));
    }

    private NovaCompraRequest request = new NovaCompraRequest("mail@mail.com", "Augusto", "Bellinaso",
            "57155281000", "rua de casa", "apto 1", "POA", 1L, "555510564", "91100000", pedido);

    @Test
    @DisplayName("cria compra com estado e cupom")
    void teste1() {
        request.setCodigoCupom("codigo-cupom");
        request.setIdEstado(1L);
        Compra novaCompra = request.toModel(manager, cupomRepository);
        Assertions.assertNotNull(novaCompra);
        Mockito.verify(manager).find(Estado.class, 1L);
        Mockito.verify(cupomRepository).getByCodigo("codigo-cupom");
    }

}
