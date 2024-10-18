package com.example.doyourlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doyourlist.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val toMain = Intent(this@SplashScreen, AddNoteActivity::class.java)
                    startActivity(toMain)
                    finish()
                }
            }
        }
        thread.start()
    }
}