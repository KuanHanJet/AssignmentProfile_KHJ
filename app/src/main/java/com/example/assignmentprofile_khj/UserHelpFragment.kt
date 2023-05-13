package com.example.assignmentprofile_khj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserHelpFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_help_user)

        //activate call_person1 button
        val callPerson1 = findViewById<FloatingActionButton>(R.id.call_person1)

        callPerson1.setOnClickListener {
            contactPerson1()
        }

        //activate call_person2 button
        val callPerson2 = findViewById<FloatingActionButton>(R.id.call_person2)

        callPerson2.setOnClickListener {
            contactPerson2()
        }

        //activate call_person3 button
        val callPerson3 = findViewById<FloatingActionButton>(R.id.call_person3)

        callPerson3.setOnClickListener {
            contactPerson3()
        }

        //activate call_person4 button
        val callPerson4 = findViewById<FloatingActionButton>(R.id.call_person4)

        callPerson4.setOnClickListener {
            contactPerson4()
        }

        //return to settings home button
        val backward = findViewById<FloatingActionButton>(R.id.back_ward2)

        backward.setOnClickListener {
            val intent = Intent(this, UserSettings::class.java)

            startActivity(intent)
        }
    }

    //function when click person1 button and directly go to phone app and have a call
    private fun contactPerson1(){
        val intent2 = Intent(Intent.ACTION_DIAL)
        intent2.data = Uri.parse("tel: 0196464586")
        startActivity(intent2)
    }

    //function when click person2 button and directly go to phone app and have a call
    private fun contactPerson2(){
        val intent3 = Intent(Intent.ACTION_DIAL)
        intent3.data = Uri.parse("tel: 0122131242")
        startActivity(intent3)
    }

    //function when click person3 button and directly go to phone app and have a call
    private fun contactPerson3(){
        val intent4 = Intent(Intent.ACTION_DIAL)
        intent4.data = Uri.parse("tel: 01121423650")
        startActivity(intent4)
    }

    //function when click person4 button and directly go to phone app and have a call
    private fun contactPerson4(){
        val intent5 = Intent(Intent.ACTION_DIAL)
        intent5.data = Uri.parse("tel: 01153286585")
        startActivity(intent5)
    }

}