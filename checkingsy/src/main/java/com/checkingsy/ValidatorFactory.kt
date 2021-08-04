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

import com.checkingsy.validators.*

abstract class ValidatorFactory {
    open fun getFullNameValidator(): Validator = FullNameValidator()
    open fun getEmailValidator(): Validator = EmailValidator()
    open fun getPasswordValidator(): Validator = PasswordValidator()
    open fun getLengthValidator(digits: Int): Validator = LengthValidator(digits)
    open fun getLimitValidator(min: Float, max: Float): Validator = RangeValidator(min, max)
    open fun getRequiredValidator(fieldName: String): Validator = RequiredValidator(fieldName)
    open fun getRegexValidator(pattern: String): Validator = RegexValidator(pattern)
}