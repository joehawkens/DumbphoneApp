package com.example.launcherapp

import android.content.ComponentName
import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.MediaStore
import android.provider.Settings
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        // Alert Dialog Box========================================================

            // Create the object of AlertDialog Builder class
            val popup = AlertDialog.Builder(this)

            // Set the message show for the Alert time
            popup.setMessage("Please install Spotify.")

            // Set Alert Title
            popup.setTitle("Error !")

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            popup.setCancelable(false)

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            //builder.setPositiveButton("Yes") {
            // When the user click yes button then app will close.
            // dialog, which -> finish()
            // }

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            popup.setNegativeButton("Ok") {
                // If user click no then dialog box is canceled.
                    dialog, _ -> dialog.cancel()
                }

            // Create the Alert dialog
            val alertDialog = popup.create()




        // Phone Button ======================================================================
        val phoneNavigation = findViewById<Button>(R.id.phoneButton)
        val phoneIntent = Intent(Intent.ACTION_DIAL)

        phoneNavigation.setOnClickListener{

            startActivity(phoneIntent)

        }

        // Text Button =======================================================================
        val textNavigation = findViewById<Button>(R.id.textButton)
        val textIntent = Intent(Intent.ACTION_VIEW)
        textIntent.data = Uri.parse("sms:")

        textNavigation.setOnClickListener{

            startActivity(textIntent)

        }

        // Timer Button =======================================================================
        val clockNavigation = findViewById<Button>(R.id.clockButton)
        val clockIntent = Intent(AlarmClock.ACTION_SET_TIMER)

        clockNavigation.setOnClickListener{

            startActivity(clockIntent)

        }


        // Maps Button =======================================================================
        val mapNavigation = findViewById<Button>(R.id.mapsButton)
        val mapIntent = Intent(Intent.ACTION_VIEW)
        mapIntent.data = Uri.parse("geo:")


        mapNavigation.setOnClickListener{
            startActivity(mapIntent)
        }

        // Calendar Button =======================================================================
        val calendarNavigation = findViewById<Button>(R.id.calendarButton)

        val startMillis = System.currentTimeMillis()
        val builder = CalendarContract.CONTENT_URI.buildUpon()
        builder.appendPath("time")
        ContentUris.appendId(builder, startMillis)
        val calendarIntent = Intent(Intent.ACTION_VIEW).setData(builder.build())


        calendarNavigation.setOnClickListener{

            startActivity(calendarIntent)

        }

        // Music Button (Spotify) =======================================================================
        val musicNavigation = findViewById<Button>(R.id.musicButton)
        val spotifyIntent = Intent(Intent.ACTION_MAIN)
        //spotifyIntent.action = MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH
        spotifyIntent.component =
                ComponentName(
                    "com.spotify.music",
                    "com.spotify.music.MainActivity"
                )

        // Return user home if they don't have Spotify downloaded
        val returnHome = Intent(Intent.ACTION_MAIN)
        returnHome.addCategory(Intent.CATEGORY_HOME)


        musicNavigation.setOnClickListener{

            if (spotifyIntent != null) {
                startActivity(spotifyIntent)
            } else {
                alertDialog.show()
            }

        }


        // Camera Button =======================================================================
        val cameraNavigation = findViewById<Button>(R.id.cameraButton)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


        cameraNavigation.setOnClickListener{

            startActivity(cameraIntent)

        }


        // Settings Button =======================================================================
        val settingsNavigation = findViewById<Button>(R.id.settingsButton)
        val callHomeSettingIntent = Intent(Settings.ACTION_HOME_SETTINGS)
        settingsNavigation.setOnClickListener{
            startActivity(callHomeSettingIntent)
        }


    }
}