package com.example.movieappmad23

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieappmad23.navigation.Navigation
import com.example.movieappmad23.ui.theme.MovieAppMAD23Theme

class MainActivity : ComponentActivity() {
    // variable instantiations should go into onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppMAD23Theme {
                Navigation()
            }
        }
    }

    // Activity becomes visible to the users
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called.")
    }

    // Activity is interactive and running in foreground
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume called.")
    }

    // Activity loses foreground state (eg. another app is interrupting the process) but we can still see it
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause called.")
    }

    // Activity is no longer visible to the user
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called.")
    }

    // App is transitioning from onStop back to foreground
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart called.")
    }

    // Activity is destroyed -> process is killed or shut down by the user
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called.")
    }
}

