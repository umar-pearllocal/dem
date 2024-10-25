package com.example.demo

import TextAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.databinding.FragmentABinding


class FragmentA : Fragment()  {
    lateinit var binding: FragmentABinding
    lateinit var adapter: TextAdapter
    private val items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentABinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        items.addAll(List(10) { "Item ${it + 1}" })
        adapter = TextAdapter(items)
        binding.recyclerView.adapter=adapter
    }

    fun getAAdapter(): TextAdapter {
        return adapter // This will work only after onViewCreated has run
    }

}