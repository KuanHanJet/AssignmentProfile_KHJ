package com.example.assignmentprofile_khj

import android.view.View
import android.widget.TextView

class Validation {

    fun nullValueCheck(inputText: String, field: String): String {
        //If input text is null
        return if (inputText.isNullOrBlank()) {
            //Return error message
            "Please enter your $field."
        } else {
            ""
        }
    }

    fun formatCheck(inputText: String, field: String): String {
        //If match either format, return no error message, vice versa
        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(inputText).matches()) {
            ""
        } else if (android.util.Patterns.PHONE.matcher(inputText).matches()) {
            ""
        } else {
            "Please ensure that your $field is in correct format."
        }
    }

    fun sizeCheck(inputText: String, field: String): String {
        return if (inputText.length < 8) {
            "Please ensure that your $field is at least 8 characters."
        } else {
            ""
        }
    }

    fun errorText(inputText: TextView, errorMsg: String): Boolean {
        //If there is an error message
        return if (errorMsg != "") {
            inputText.visibility = View.VISIBLE
            inputText.text = errorMsg

            false
        } else {
            inputText.visibility = View.INVISIBLE
            true
        }
    }
}