package com.checkingsy.samples

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.checkingsy.Checkingsy
import com.checkingsy.samples.databinding.ActivityMainBinding
import com.checkingsy.validate
import com.checkingsy.validation.ValidationException
import com.checkingsy.validation.ValidationType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**
         * Registering a custom validator factory.
         */
        Checkingsy.registerValidatorFactory(MyCustomValidatorFactory)

        binding.submitButton.setOnClickListener {
            if (verifyUserInput()) {
                Toast.makeText(this, "Verification done! Ready to send to server. :)", Toast.LENGTH_SHORT).show()
            }
        }

        val randomNumberArray = mutableListOf<String>().apply {
            add("Pick a Random Number")
            repeat(10) { add(it.toString()) }
        }
        binding.randomDigitSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, randomNumberArray)
    }

    private fun verifyUserInput(): Boolean {

        try {
            with(binding) {
                fullNameEditText.validate(ValidationType.FullName)
                emailEditText.validate(ValidationType.Email)
                ageEditText.validate(ValidationType.Range(14F, 80F), false, "You age should be in between 14 to 80 year old")
                phoneNumberEditText.validate(ValidationType.Length(10), errorMessage = "Invalid phone number!")
                passwordEditText.validate(ValidationType.Password)
                aboutEditText.validate(ValidationType.Required("About"))
                randomDigitSpinner.validate(ValidationType.Required("Random Number"))
            }
        } catch (exception: ValidationException) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            exception.view.requestFocus()
            return false
        }
        return true
    }
}