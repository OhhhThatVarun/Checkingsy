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

package com.checkingsy.validators

import android.view.View
import android.widget.EditText

internal class LengthValidator(private val length: Int) : Validator() {

    override fun validate(view: View, required: Boolean, errorMessage: String?) {
        super.validate(view, required, errorMessage)

        (view as? EditText)?.let {
            val input: CharSequence = it.text.trim()
            if (input.length != length) {
                throw validationException(it, errorMessage)
            }
        }
    }

    override fun getErrorMessage(): String {
        return "Required length is $length"
    }
}