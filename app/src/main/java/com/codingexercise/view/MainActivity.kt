package com.codingexercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingexercise.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        title = "Account details"
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.icon_welcome_logo)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransactionsFragment.newInstance())
                .commitNow()
        }
    }

}
