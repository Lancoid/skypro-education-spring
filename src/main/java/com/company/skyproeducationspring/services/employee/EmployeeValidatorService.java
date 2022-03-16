package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeValidatorException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeValidatorService implements EmployeeValidatorServiceInterface {

    @Override
    public String validateFirstName(String firstName) {
        String result = StringUtils.capitalize(firstName.trim());

        if (StringUtils.isEmpty(result) || !StringUtils.isAlpha(result)) {
            throw new EmployeeValidatorException("Имя сотрудника обязательно к заполнению и должно состоять из букв");
        }

        return result;
    }

    @Override
    public String validateLastName(String lastName) {
        String result = StringUtils.capitalize(lastName.trim());

        if (StringUtils.isEmpty(result) || !StringUtils.isAlpha(result)) {
            throw new EmployeeValidatorException("Фамилия сотрудника обязательна к заполнению и должно состоять из букв");
        }

        return result;
    }
}
