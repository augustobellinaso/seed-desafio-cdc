package com.augustobellinaso.casadocodigo.finalizacompra;

import com.augustobellinaso.casadocodigo.cadastrocupom.Cupom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CupomValidoValidator implements Validator {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;

        Optional<String> possivelCodigo = request.getCodigoCupom();
        if (possivelCodigo.isPresent()) {
            Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
            if (!cupom.valido()) {
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
            }
        }
    }
}
