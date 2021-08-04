package com.checkingsy.samples

import com.checkingsy.ValidatorFactory
import com.checkingsy.validators.Validator

object MyCustomValidatorFactory : ValidatorFactory() {

    override fun getPasswordValidator(): Validator {
        return MyCustomPasswordValidator
    }
}