package com.example.doyourlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var note: List<Note> ,context: Context) :


    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

        private val db : NoteDataBase = NoteDataBase(context)

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
        val editButton: ImageView = itemView.findViewById(R.id.edit)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_row,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = note.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = note[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.editButton.setOnClickListener {
            val intent = Intent(holder.itemView.context,updateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Delete Note")
            builder.setMessage("Are you sure you want to delete this note?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                db.deleteNodeById(note.id)
                refreshData(db.getAllNotes())
                Toast.makeText(holder.itemView.context, "Note deleted successfully", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog , _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
    fun refreshData(newNotes:List<Note>){
        note = newNotes
        notifyDataSetChanged()
    }
}