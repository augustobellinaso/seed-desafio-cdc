package com.augustobellinaso.casadocodigo.cadastrocupom;

import com.augustobellinaso.casadocodigo.compartilhado.CustomMockMvc;
import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.time.api.Dates;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class NovoCupomControllerTest {

    private Set<String> unicos = new HashSet<>();
    @Autowired
    private CustomMockMvc mvc;

    @Property(tries = 10)
    @Label("fluxo de cadastro de novo cupom")
    void teste(
            @ForAll @AlphaChars @StringLength(min = 1, max = 255) String codigo,
            @ForAll @BigRange(min = "1", max = "90") BigDecimal percentualDesconto,
            @ForAll("datasFuturas") LocalDate validade) throws Exception {

        Assumptions.assumeTrue(unicos.add(codigo));
        String validadeFormatada = validade
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        mvc.post("/cupons",
                        Map.of("codigo", codigo, "percentualDesconto",
                                percentualDesconto.toString(), "dataValidade", validadeFormatada))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mvc.post("/cupons",
                        Map.of("codigo", codigo, "percentualDesconto",
                                percentualDesconto.toString(), "dataValidade", validadeFormatada))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Provide
    Arbitrary<LocalDate> datasFuturas() {
        return Dates.dates().atTheEarliest(LocalDate.now().plusDays(1));
    }
}
