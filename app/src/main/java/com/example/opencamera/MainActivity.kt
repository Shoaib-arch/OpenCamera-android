package com.example.opencamera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // perform speech-to-text using an intent
        // perform speech-to-text using an intent
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
// prompt tells user what to say
// prompt tells user what to say
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "text")
        startActivityForResult(intent, 42)
    }

    fun captureImage(view: View){
        var takePics= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePics.resolveActivity(this.packageManager)!=null) {
            startActivityForResult(takePics, 42)
        }
        else{
            startActivityForResult(takePics, 42)
            Toast.makeText(this,"Unable to open",Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           val iv= findViewById<ImageView>(R.id.imageView)
        if(requestCode==42 && resultCode== Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
            iv.setImageBitmap(takenImage)
        }else
        super.onActivityResult(requestCode, resultCode, data)
    }
}