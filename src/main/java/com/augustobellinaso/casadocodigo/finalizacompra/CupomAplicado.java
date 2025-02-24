package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    @Positive
    @NotNull
    private BigDecimal percentualDescontoMomento;

    @NotNull
    @Future
    private LocalDate validadeMomento;

    @Deprecated(since = "1.0.0")
    public CupomAplicado() {}

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getDataValidade();
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentualDescontoMomento=" + percentualDescontoMomento +
                ", validadeMomento=" + validadeMomento +
                '}';
    }
}
