package com.example.assignmentprofile_khj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class UserAllBadgesFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_all_badges_user)

        //display username (login email text pass to user profile, open sqllite helper, use conditionalgetattribute function pass in username, emailtext, conditionalattribute return a string,
        //(continue) display username = conditionalgetattribute

        //activate all badges button and go to the settings page
        val settings = findViewById<Button>(R.id.s_e_t_t_i_n_g_s2)

        settings.setOnClickListener {
            val intent2 = Intent (this, UserSettings::class.java)

            startActivity(intent2)
        }
    }

}