package com.example.doyourlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doyourlist.databinding.ActivityUpdateBinding

class updateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db : NoteDataBase
    private var noteId : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDataBase(this)
        val noteId = intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }
        val note = db.getNoteByID(noteId)
        binding.title.setText(note.title)
        binding.content.setText(note.content)
        binding.gobackactivity.setOnClickListener {
            startActivity(Intent(this@updateNoteActivity,AddNoteActivity::class.java))
            finish()
        }
        binding.submit.setOnClickListener {
            val newTitle = binding.title.text.toString()
            val newContent = binding.content.text.toString()
            if (newTitle.isNotEmpty() && newContent.isNotEmpty()){
                val updatedNote = Note(noteId,newTitle,newContent)
                db.updateNote(updatedNote)
                Toast.makeText(this, "note updated successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@updateNoteActivity,AddNoteActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "you must fill the required information", Toast.LENGTH_SHORT).show()
            }

        }
    }
}