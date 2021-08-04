/**
 * Copyright (C) 2021 Varun Raj Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.checkingsy

import com.checkingsy.validation.ValidationType
import com.checkingsy.validators.Validator

object Checkingsy {

    private var validatorFactory: ValidatorFactory = CheckingsyValidatorFactory

    fun registerValidatorFactory(validatorFactory: ValidatorFactory) {
        this.validatorFactory = validatorFactory
    }

    fun resetToDefaultValidatorFactory() {
        this.validatorFactory = CheckingsyValidatorFactory
    }

    internal fun validateFor(type: ValidationType): Validator {
        return with(validatorFactory) {
            when (type) {
                is ValidationType.FullName -> getFullNameValidator()
                is ValidationType.Email -> getEmailValidator()
                is ValidationType.Password -> getPasswordValidator()
                is ValidationType.Length -> getLengthValidator(type.length)
                is ValidationType.Range -> getLimitValidator(type.min, type.max)
                is ValidationType.Required -> getRequiredValidator(type.fieldName)
                is ValidationType.Regex -> getRegexValidator(type.pattern)
            }
        }
    }
}