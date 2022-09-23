package com.internshipcodes.groceryapp
/*
This activity used to create a splash screen of the app
 */
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find the imageview in layout file and assign it to a value
        val splashScreen = findViewById<ImageView>(R.id.splashScr)

        //create animation and go to next activity(SecondActivity) after the duration set
        //make sure that this activity no longer preview after going to next activity by finish() method
        splashScreen.alpha = 0f
        splashScreen.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this,SecondActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}