package com.augustobellinaso.casadocodigo.compartilhado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String domainAttribute;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        clazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + clazz.getName()
                + " WHERE " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> result = query.getResultList();
        Assert.state(result.size() <= 1, "Foi encontrado mais de um " + clazz + " com o atributo " + domainAttribute + " = " + value);

        return !result.isEmpty();
    }
}
