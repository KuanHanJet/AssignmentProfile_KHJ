package com.example.assignmentprofile_khj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserAllBadgesFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_all_badges_user)

        //activate all badges button and go to the settings page
        val settings = findViewById<Button>(R.id.s_e_t_t_i_n_g_s2)

        settings.setOnClickListener {
            val intent2 = Intent (this, UserSettings::class.java)

            startActivity(intent2)
        }
    }
}