package com.augustobellinaso.casadocodigo.cadastrocupom;

import com.augustobellinaso.casadocodigo.compartilhado.UniqueValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal percentualDesconto;

    @Future
    @NotNull
    private LocalDate dataValidade;

    @Deprecated(since = "1.0.0")
    public Cupom() {}

    public Cupom(@NotBlank String codigo,
                 @NotNull @Positive BigDecimal percentualDesconto,
                 @NotNull @Future LocalDate dataValidade) {
        Assert.isTrue(dataValidade.compareTo(LocalDate.now()) >= 0, "A validade deve ser uma data futura");
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "codigo='" + codigo + '\'' +
                ", percentualDesconto=" + percentualDesconto +
                ", dataValidade=" + dataValidade +
                '}';
    }

    public boolean valido() {
        return !LocalDate.now().isAfter(this.dataValidade);
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
}
