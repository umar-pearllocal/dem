package com.example.demo

import android.os.Bundle
import android.provider.MediaStore.Audio.Playlists.Members.moveItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var fragmentA: FragmentA
    private lateinit var fragmentB: FragmentB
    private var selectedItem: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_left, FragmentA())
            replace(R.id.fragment_container_right, FragmentB())
            commit()
        }
        fragmentA = FragmentA()
        fragmentB = FragmentB()
        initListeners()
    }

    private fun initListeners() {
        binding.btnMoveRight.setOnClickListener {
            if (::fragmentA.isInitialized && ::fragmentB.isInitialized) {
                moveItemsAtoB()
            }
        }

        binding.btnMoveLeft.setOnClickListener {
            if (::fragmentA.isInitialized && ::fragmentB.isInitialized) {
                moveItemsBtoA()
            }
        }
    }

    private fun moveItemsAtoB() {
        val selectedItems = fragmentA.getAAdapter().getSelectedItems() // Ensure the adapter is accessed correctly
        if (selectedItems.isNotEmpty()) {
            fragmentA.getAAdapter().removeSelectedItems()
            fragmentB.getBAdapter().addItems(selectedItems)
        }
    }

    private fun moveItemsBtoA() {
        val selectedItems = fragmentB.getBAdapter().getSelectedItems()
            fragmentB.getBAdapter().removeSelectedItems()
            fragmentA.getAAdapter().addItems(selectedItems)
        }
    }

