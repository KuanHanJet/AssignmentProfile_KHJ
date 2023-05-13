package com.example.assignmentprofile_khj

/**
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentprofile_khj.database.UserSQLiteHelper
import com.example.assignmentprofile_khj.databinding.LoginBinding
*/

/**
class Login : AppCompatActivity() {

    //Set binding variables
    private lateinit var binding: LoginBinding

    //Use validation methods
    private lateinit var validation: Validation

    //Use SQL helper
    private lateinit var sqliteHelper: UserSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //Initialization
        binding = LoginBinding.inflate(layoutInflater)
        validation = Validation()
        setContentView(binding.root)

        sqliteHelper = UserSQLiteHelper(this)
        Log.i("Main Activity", "${sqliteHelper.getAllUser()}")

        //Navigate to SignUp page
        binding.loginNavigate.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        binding.loginButton.setOnClickListener {
            //Do validation
            val idBool = validation.errorText(binding.loginIDError, validation(binding.loginID))
            val passwordBool =
                validation.errorText(binding.loginPasswordError, validation(binding.loginPassword))

            if (idBool && passwordBool) {
                login()
            }
        }

        var clickTimes = 0

        //Hide and unhide password
        binding.loginUnhide.setOnClickListener {

            clickTimes++
            showPassword(clickTimes)
        }
    }

    private fun validation(inputField: EditText): String {
        //Check the input field passed
        when (inputField) {

            binding.loginID -> {
                //Set error text
                //Check the type of error occurred
                //Logic: Pass the input field and field type to check if there is any error message (error occured). If any error, will return an error message.
                return if (validation.nullValueCheck(
                        inputField.text.toString(),
                        "email or phone number"
                    ) != ""
                ) {
                    validation.nullValueCheck(inputField.text.toString(), "email or phone number")
                } else if (validation.formatCheck(
                        inputField.text.toString(),
                        "email or phone number"
                    ) != ""
                ) {
                    validation.formatCheck(inputField.text.toString(), "email or phone number")
                } else if (loginExistenceCheck(
                        inputField.text.toString(),
                        sqliteHelper.getAttribute("contactInfo")
                    ) != ""
                ) {
                    loginExistenceCheck(
                        inputField.text.toString(),
                        sqliteHelper.getAttribute("contactInfo")
                    )
                } else {
                    ""
                }
            }

            binding.loginPassword -> {

                return if (validation.nullValueCheck(
                        inputField.text.toString(),
                        "password"
                    ) != ""
                ) {
                    validation.nullValueCheck(inputField.text.toString(), "password")
                } else if (passwordCheck(
                        binding.loginID.text.toString(),
                        inputField.text.toString()
                    ) != ""
                ) {
                    passwordCheck(binding.loginID.text.toString(), inputField.text.toString())
                } else {
                    ""
                }
            }

            else -> {}
        }

        return ""
    }

    private fun showPassword(clickTimes: Int) {
        //If user click the eye button (Times clicked)
        if (clickTimes % 2 == 1) {
            //Unhide button
            binding.loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            //Hide button
            binding.loginPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
    }

    private fun loginExistenceCheck(inputText: String, attributeList: ArrayList<String>): String {
        for (i in attributeList) {
            if (inputText == i) {
                return ""
            }
        }

        //Only reachable if no record matches
        return "Please ensure that your email or phone number exists."
    }

    private fun passwordCheck(contactInfo: String, password: String): String {
        try {
            if (sqliteHelper.conditionalGetAttribute(
                    "contactInfo",
                    "password", password
                ) != contactInfo
            ) {
                return "Your password is incorrect. Please enter your password again."
            }
        } catch (ex: Exception) {
            return "Your password is incorrect. Please enter your password again."
        }
        return ""
    }

    private fun login() {
        Log.i("Main Activity", "Login succeed")
        startActivity(
            Intent(
                this,
                UserSettings::class.java
            )
        )
    }
}
        **/