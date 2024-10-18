package com.example.doyourlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doyourlist.databinding.ActivityAddNoteBinding
import com.example.doyourlist.databinding.ActivityCreateNoteBinding

class createNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateNoteBinding
    private lateinit var noteDataBaseHelper: NoteDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteDataBaseHelper = NoteDataBase(this)
        binding.gobackactivity.setOnClickListener {
            startActivity(Intent(this@createNoteActivity, AddNoteActivity::class.java))
            finish()
        }
        binding.submit.setOnClickListener { 
            val title = binding.title.text.toString()
            val content = binding.content.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()){
                val note = Note(0,title,content)
                noteDataBaseHelper.insertNode(note)
                startActivity(Intent(this@createNoteActivity,AddNoteActivity::class.java))
                Toast.makeText(this, "Note Added Successfully !", Toast.LENGTH_SHORT).show()
                finish()    
            }else{
                Toast.makeText(this, "you must fill the required information !", Toast.LENGTH_SHORT).show()
            }
            
        }
    }
}