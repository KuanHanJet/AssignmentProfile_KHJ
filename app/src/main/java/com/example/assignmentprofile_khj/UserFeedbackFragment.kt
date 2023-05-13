package com.example.assignmentprofile_khj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserFeedbackFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_feedback_user)

        //rating star
        val appRating = findViewById<RatingBar>(R.id.app_rating)
        val appRatingText = findViewById<TextView>(R.id.app_rating_text)

        //radio group
        val feedbackCondition = findViewById<RadioGroup>(R.id.feedback_condition)

        //feedback input text
        val feedbackInput = findViewById<EditText>(R.id.feedback_input)

        //click rating star and display the rating star description
        appRating.setOnRatingBarChangeListener { ratingStar, ratingText, booLean ->
            appRatingText.text = ratingText.toString()
            when (ratingStar.rating.toInt()){
                1 -> appRatingText.text = "Very Disappointed"
                2 -> appRatingText.text = "Disappointed"
                3 -> appRatingText.text = "Normal"
                4 -> appRatingText.text = "Best"
                5 -> appRatingText.text = "Very Best"
                else -> appRatingText.text = ""
            }
        }

        //cancel and back to settings page button
        val cancel = findViewById<Button>(R.id.feedback_cancel)

        cancel.setOnClickListener {
            val intent = Intent(this, UserSettings::class.java)

            startActivity(intent)
        }

        //reset the value button
        val reset = findViewById<Button>(R.id.feedback_reset)

        reset.setOnClickListener {
            appRating.setRating(0.0f)
            appRatingText.text = ""
            feedbackCondition.clearCheck()
            feedbackInput.setText("")
        }

        //submit button
        val submit = findViewById<Button>(R.id.feedback_submit)

        submit.setOnClickListener {

            Toast.makeText(this, "Thank you for your feedback and review", Toast.LENGTH_SHORT).show()
        }

        //return to settings page button
        val backward = findViewById<FloatingActionButton>(R.id.back_ward3)

        backward.setOnClickListener {
            val intent3 = Intent(this, UserSettings::class.java)

            startActivity(intent3)
        }
    }
}