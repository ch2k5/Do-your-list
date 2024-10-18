package com.example.doyourlist

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doyourlist.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var db : NoteDataBase
    private lateinit var notesAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = NoteDataBase(this)
        notesAdapter = NoteAdapter(db.getAllNotes(),this)

        binding.noterecyclerview.layoutManager = LinearLayoutManager(this)
        binding.noterecyclerview.adapter = notesAdapter

        binding.addList.setOnClickListener {
            startActivity(Intent(this@AddNoteActivity,createNoteActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}