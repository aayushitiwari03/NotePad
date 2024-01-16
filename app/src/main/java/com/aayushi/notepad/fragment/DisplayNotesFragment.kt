package com.aayushi.notepad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.aayushi.notepad.MainActivity
import com.aayushi.notepad.R
import com.aayushi.notepad.databinding.FragmentDisplayNotesBinding

class DisplayNotesFragment : Fragment() {
    lateinit var binding: FragmentDisplayNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_display_notes,
            container,
            false
        )

        binding.floatingActionButton2.setOnClickListener {
            (activity as MainActivity).findNavController(R.id.nav_host_graph)
                .navigate(R.id.action_displayNotesFragment_to_addFragment)
        }
        return binding.root
    }

}