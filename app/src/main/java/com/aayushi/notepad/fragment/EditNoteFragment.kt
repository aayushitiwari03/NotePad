package com.aayushi.notepad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aayushi.notepad.R
import com.aayushi.notepad.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {
    lateinit var binding: FragmentEditNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_edit_note,
            container,
            false
        )
        return binding.root
    }

}