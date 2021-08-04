package com.checkingsy.samples

import android.view.View
import android.widget.EditText
import com.checkingsy.validators.Validator

/**
 * Custom password requirement can be checked here.
 */
object MyCustomPasswordValidator : Validator() {

    override fun validate(view: View, required: Boolean, errorMessage: String?) {
        super.validate(view, required, errorMessage)
        (view as? EditText)?.let {

            val input = it.text.trim()
            if (input.length == 5 && !input.contains("#")) {
                throw validationException(it, errorMessage)
            }
        }
    }

    override fun getErrorMessage(): String {
        return "Password must contain # and should contain 5 character."
    }
}