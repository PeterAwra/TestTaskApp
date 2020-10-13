package com.awra.stud.testtaskapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.awra.stud.testtask.game.GameFragment
import com.awra.stud.testtaskapp.webview.WebViewFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportFragmentManager.beginTransaction().replace(
        R.id.fragment_container,
        if (getRunMode()) WebViewFragment() else GameFragment()
    ).commit()
  }
}
