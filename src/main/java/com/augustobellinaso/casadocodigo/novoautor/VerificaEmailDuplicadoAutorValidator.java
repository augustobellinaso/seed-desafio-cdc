package com.augustobellinaso.casadocodigo.novoautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class VerificaEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //so aplica a validacao se nao caiu em nenhuma outra do spring
        if (errors.hasErrors()) {
            return ;
        }

        NovoAutorRequest request = (NovoAutorRequest) target;
        Optional<Autor> autor = autorRepository.findByEmail(request.getEmail());

        if (autor.isPresent()) {
            errors.rejectValue("email", null, "E-mail informado já cadastrado " + request.getEmail());
        }

    }
}
