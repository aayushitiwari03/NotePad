package com.aayushi.notepad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.aayushi.notepad.databinding.NotesListBinding
import com.aayushi.notepad.rdb.Notes
import com.google.android.material.snackbar.Snackbar

class NotesAdapter(
    private val notes: List<Notes>,
    private val onDeleteClickListener: (Notes) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(
        val binding: NotesListBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(note: Notes) {
            binding.apply {
                noteTitle.text = note.noteTitle
                noteDescription.text = note.note_Description
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)

        holder.binding.deleteNote.setOnClickListener {
            onDeleteClickListener.invoke(note)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            Snackbar.make(it, "Note Deleted", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    //insert the note again
                }
                show()
            }
        }
    }
}

