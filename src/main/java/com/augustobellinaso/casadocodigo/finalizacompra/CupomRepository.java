package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends org.springframework.data.repository.Repository<Cupom, Long> {

    /**
     * Busca por um cupom que existe no sistema
     * @param codigo
     * @return
     */
    Cupom getByCodigo(String codigo);
}
