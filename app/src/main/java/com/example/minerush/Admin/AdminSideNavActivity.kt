package com.example.minerush.Admin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.LoginActivity
import com.example.minerush.R
import com.example.minerush.databinding.ActivityAdminSideNavBinding

class AdminSideNavActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAdminSideNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminSideNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarAdminSideNav.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_admin_side_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.admin_home, R.id.admin_about, R.id.admin_contact_us, R.id.admin_my_profile,R.id.admin_logout
            ), drawerLayout
        )
        navView.menu.findItem(R.id.admin_logout).setOnMenuItemClickListener {
            val intent = Intent(
                this@AdminSideNavActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
            true
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.admin_side_nav, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_admin_side_nav)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}