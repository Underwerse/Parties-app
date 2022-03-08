package fi.example.parties

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fi.example.parties.databinding.ActivityMainBinding

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * The app's Main-activity class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // make Navigation controller
        val navController = this.findNavController(R.id.myNavHostFragment)
        // link Navigation controller to the app bar
        NavigationUI.setupActionBarWithNavController(this,navController)

        appContext = this // for using app-context wherever it's needed
    }

    companion object {
        lateinit var appContext: Context
    }

    // make Up-bar navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}