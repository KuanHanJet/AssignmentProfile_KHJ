package com.example.assignmentprofile_khj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserSettings : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_user)

        //display username (login email text pass to user profile, open sqllite helper, use conditionalgetattribute function pass in username, emailtext, conditionalattribute return a string,
        //(continue) display username = conditionalgetattribute


        //activate all badges button and go to the all badges page
        val all_badges = findViewById<Button>(R.id.all_badges)

        all_badges.setOnClickListener {
            val intent2 = Intent (this, UserAllBadgesFragment::class.java)

            startActivity(intent2)
        }

        //activate all badges button and go to the profile settings page
        val profile_settings = findViewById<Button>(R.id.profile_settings)

        profile_settings.setOnClickListener {
            val intent3 = Intent (this, UserProfileSettingsFragment::class.java)

            startActivity(intent3)
        }

        //activate all badges button and go to the help page
        val help = findViewById<Button>(R.id.h_e_l_p)

        help.setOnClickListener {
            val intent4 = Intent (this, UserHelpFragment::class.java)

            startActivity(intent4)
        }

        //activate all badges button and go to the feedback page
        val feedback = findViewById<Button>(R.id.f_e_e_d_b_a_c_k)

        feedback.setOnClickListener {
            val intent5 = Intent (this, UserFeedbackFragment::class.java)

            startActivity(intent5)
        }

        //activate all badges button and go to the login page
        //val logOut = findViewById<Button>(R.id.log_out)

        //logOut.setOnClickListener {
           // val intent6 = Intent(this, Login::class.java)

            //startActivity(intent6)
       // }
    }
}