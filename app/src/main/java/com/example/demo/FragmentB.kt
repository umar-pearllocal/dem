package com.example.demo

import TextAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.databinding.FragmentABinding
import com.example.demo.databinding.FragmentBBinding

class FragmentB : Fragment() {
    lateinit var binding: FragmentBBinding
    lateinit var adapter: TextAdapter
    private val items = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TextAdapter(items)
        binding.recyclerView.adapter = adapter
    }
    fun getBAdapter(): TextAdapter {
        return adapter
    }


}