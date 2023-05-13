package com.example.assignmentprofile_khj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.assignmentprofile_khj.database.UserModel
import com.example.assignmentprofile_khj.database.UserSQLiteHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserProfileSettingsFragment : AppCompatActivity() {

    private lateinit var model: UserModel
    private lateinit var sqliteHelper: UserSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile_settings_user)

        var username = findViewById<TextView>(R.id.display_username_profile_settings)

        var info = findViewById<TextView>(R.id.display_email_phoneNum)

        var password = findViewById<TextView>(R.id.display_password)

        //display
        sqliteHelper.getRecord(us)

        //return to settings page button
        val backward = findViewById<FloatingActionButton>(R.id.back_ward)
        backward.setOnClickListener {
            val intent = Intent(this, UserSettings::class.java)

            startActivity(intent)
        }
    }
}