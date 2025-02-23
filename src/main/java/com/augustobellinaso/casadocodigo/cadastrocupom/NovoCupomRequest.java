package com.augustobellinaso.casadocodigo.cadastrocupom;

import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoCupomRequest {

    @NotBlank
    @UniqueValue(fieldName = "codigo", domainClass = Cupom.class)
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal percentualDesconto;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataValidade;

    public NovoCupomRequest(@NotBlank String codigo,
                            @NotNull @Positive BigDecimal percentualDesconto) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return "NovoCupomRequest{" +
                "codigo='" + codigo + '\'' +
                ", percentualDesconto=" + percentualDesconto +
                ", dataValidade=" + dataValidade +
                '}';
    }

    public Cupom toModel() {
        return new Cupom(codigo, percentualDesconto, dataValidade);
    }
}
