package com.example.assignmentprofile_khj

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import android.os.Build
import android.os.Build.VERSION_CODES
import android.provider.MediaStore

class UserSettings : AppCompatActivity() {

    private lateinit var profilePicture: ImageView
    private lateinit var choosePicture: ImageButton
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_user)

        //display username (login email text pass to user profile, open sqllite helper, use conditionalgetattribute function pass in username, emailtext, conditionalattribute return a string,
        //(continue) display username = conditionalgetattribute

        //profile picture
        profilePicture = findViewById(R.id.profile_pic)
        choosePicture = findViewById(R.id.choose_profile_pic)

        //activate image button and choose picture
        choosePicture.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

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

    //function take picture from gallery and display the profile picture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profilePicture.setImageURI(imageUri)
        }
    }

}