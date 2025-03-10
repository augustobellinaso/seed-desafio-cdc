package com.augustobellinaso.casadocodigo.cadastrocupom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

class CupomTest {

    @ParameterizedTest
    @CsvSource({
            "0,true",
            "1,true"
    })
    void teste1(long valor, boolean resultado) {
        Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(valor));
        Assertions.assertEquals(resultado, cupom.valido());
    }

    @Test
    @DisplayName("cupom com data no passado não é mais valido")
    void teste2() {
        Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1L));
        ReflectionTestUtils.setField(cupom, "dataValidade", LocalDate.now().minusDays(1L));
        Assertions.assertFalse(cupom.valido());
    }

    @Test
    @DisplayName("não pode criar cupom com data do passado")
    void teste3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cupom("codigo", BigDecimal.TEN, LocalDate.now().minusDays(1L));
        });
    }
}
