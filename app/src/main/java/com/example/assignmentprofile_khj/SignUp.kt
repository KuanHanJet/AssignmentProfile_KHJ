package com.example.assignmentprofile_khj

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentprofile_khj.database.UserModel
import com.example.assignmentprofile_khj.databinding.SignUpBinding
import com.example.assignmentprofile_khj.database.UserSQLiteHelper

/**
class SignUp : AppCompatActivity() {

    //Do initialization
    private lateinit var binding: SignUpBinding
    private lateinit var validation: Validation

    private lateinit var sqliteHelper: UserSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        validation = Validation()
        setContentView(binding.root)

        sqliteHelper = UserSQLiteHelper(this)

        //Navigate to Login page
        binding.signUpNavigate.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        //Populate Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.role,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.roleSelection.adapter = adapter

        //Determine role
        var role = ""

        //Whether to disable company field
        binding.roleSelection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long,
            ) {
                role = parent?.getItemAtPosition(position).toString()

                enableCompanyField(role)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.signUpButton.setOnClickListener {
            //Do all the validation (Logic same as the Login page)
            val usernameBool = validation.errorText(
                binding.signUpUsernameError,
                validation(binding.signUpUsername)
            )
            val idBool = validation.errorText(binding.signUpIDError, validation(binding.signUpID))
            val passwordBool = validation.errorText(
                binding.signUpPasswordError,
                validation(binding.signUpPassword)
            )
            val reenterBool =
                validation.errorText(binding.signUpReenterError, validation(binding.signUpReenter))
            var companyBool: Boolean

            if (role == "Company") {
                companyBool = validation.errorText(
                    binding.signUpCompanyError,
                    validation(binding.signUpCompany)
                )

                if (usernameBool && idBool && passwordBool && reenterBool && companyBool) {
                    pendingCheck()
                }
            } else if (role == "Job Finder") {
                if (usernameBool && idBool && passwordBool && reenterBool) {
                    signUpUser("Job Finder")
                }
            }
        }

        var passwordClickTimes = 0
        var reenterClickTimes = 0

        binding.signUpPasswordUnhide.setOnClickListener {
            passwordClickTimes++
            showPassword(passwordClickTimes, binding.signUpPassword)
        }

        binding.signUpReenterUnhide.setOnClickListener {
            reenterClickTimes++
            showPassword(reenterClickTimes, binding.signUpReenter)
        }
    }

    //Do validation (Logic same as Login page)
    private fun validation(inputField: EditText): String {
        //Check the input field passed
        when (inputField) {

            binding.signUpUsername -> {
                //Set error text
                val field = "username"

                //Check the type of error occurred
                return if (validation.nullValueCheck(inputField.text.toString(), field) != "") {
                    validation.nullValueCheck(inputField.text.toString(), field)
                } else if (signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("username")
                    ) != ""
                ) {
                    signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("username")
                    )
                } else {
                    ""
                }
            }

            binding.signUpID -> {
                val field = "email or phone number"

                return if (validation.nullValueCheck(inputField.text.toString(), field) != "") {
                    validation.nullValueCheck(inputField.text.toString(), field)
                } else if (validation.formatCheck(inputField.text.toString(), field) != "") {
                    validation.formatCheck(inputField.text.toString(), field)
                } else if (signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("contactInfo")
                    ) != ""
                ) {
                    signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("contactInfo")
                    )
                } else {
                    ""
                }
            }

            binding.signUpPassword -> {
                val field = "password"

                return if (validation.nullValueCheck(inputField.text.toString(), field) != "") {
                    validation.nullValueCheck(inputField.text.toString(), field)
                } else if (validation.sizeCheck(inputField.text.toString(), field) != "") {
                    validation.sizeCheck(inputField.text.toString(), field)
                } else {
                    ""
                }
            }

            binding.signUpReenter -> {
                val field = "re-entered password"

                return if (validation.nullValueCheck(inputField.text.toString(), field) != "") {
                    validation.nullValueCheck(inputField.text.toString(), field)
                } else if (matchPassword(inputField.text.toString()) != "") {
                    matchPassword(inputField.text.toString())
                } else {
                    ""
                }
            }

            binding.signUpCompany -> {
                val field = "company"

                return if (validation.nullValueCheck(inputField.text.toString(), field) != "") {
                    validation.nullValueCheck(inputField.text.toString(), field)
                } else if (signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("companyName")
                    ) != ""
                ) {
                    signUpExistenceCheck(
                        inputField.text.toString(),
                        field,
                        sqliteHelper.getAttribute("companyName")
                    )
                } else {
                    ""
                }
            }

            else -> {}
        }

        return ""
    }

    //Whether to enable company field (If role is company, enable it, vice versa)
    private fun enableCompanyField(role: String) {
        if (role == "Job Finder") {
            binding.signUpCompany.isEnabled = false
            binding.signUpCompany.isFocusable = false
            binding.signUpCompany.isClickable = false

            binding.signUpCompany.setText("")
        } else if (role == "Company") {
            binding.signUpCompany.isEnabled = true
            binding.signUpCompany.isFocusableInTouchMode = true
            binding.signUpCompany.isClickable = true
        }
    }

    //Check if password matches the reentered password
    private fun matchPassword(reenteredPassword: String): String {
        Log.i("Main Activity", "${reenteredPassword != binding.signUpPassword.text.toString()}")
        return if (reenteredPassword != binding.signUpPassword.text.toString()) {
            "Please ensure that the reentered password is the same as the password."
        } else {
            ""
        }
    }

    private fun showPassword(clickTimes: Int, passwordField: EditText) {
        //If user click the eye button
        if (clickTimes % 2 == 1) {
            //Unhide button
            passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            //Hide button
            passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }

    fun signUpUser(roleField: String) {
        //Save the records to database
        if (sqliteHelper.insertUser(
                UserModel(
                    username = binding.signUpUsername.text.toString(),
                    contactInfo = binding.signUpID.text.toString(),
                    password = binding.signUpPassword.text.toString(),
                    role = roleField,
                    companyName = binding.signUpCompany.text.toString()
                )
            ) > -1
        ) {
            alertDialog("Your account has been successfully registered. Kindly navigate to the login page to login.")
            //startActivity(Intent(this, Login::class.java))
            Log.i("Main Activitiy", "Data written")
        } else {
            Log.i("Main Activitiy", "Data not written")
        }
    }

    private fun alertDialog(message: String) {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(message)
        alert.setPositiveButton("OK") { _, _ ->
            startActivity(Intent(this, Login::class.java))
        }

        val dialog = alert.create()
        // Disable touch outside to dismiss dialog
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }


    fun signUpExistenceCheck(
        inputText: String,
        field: String,
        attributeList: ArrayList<String>,
    ): String {
        for (i in attributeList) {
            if (inputText == i) {
                return "Please ensure that your $field don't exists."
            }
        }

        return ""
    }

    private fun pendingCheck() {
        lateinit var sqliteHelper: PendingSQLiteHelper
        sqliteHelper = PendingSQLiteHelper(this)

        if (sqliteHelper.insertPending(
                CompanyModel(
                    username = binding.signUpUsername.text.toString(),
                    companyName = binding.signUpCompany.text.toString()
                )
            ) > -1
        ) {
            alertDialog("Your account has been placed in pending order. Kindly navigate to the login page.")
            Log.i("Main Activity", "${sqliteHelper.getAllPending()}")
        } else {
            Log.i("Main Activity", "Pending not succeed")
        }
    }
}
 */