package ru.mininn.languagedetection.ui

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import ru.mininn.languagedetection.R
import ru.mininn.languagedetection.ui.detection.DetectionFragment
import ru.mininn.languagedetection.ui.history.HistoryFragment

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val detectionFragment = DetectionFragment()
    private val historyFragment = HistoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)
        initDrawer()
        initFragments(savedInstanceState == null)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_detect -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, detectionFragment).commit()
            }
            R.id.nav_history -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, historyFragment).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initFragments(isNeedReset: Boolean) {
        if (isNeedReset) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, detectionFragment)
                    .commit()
        }
    }
    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }
}
