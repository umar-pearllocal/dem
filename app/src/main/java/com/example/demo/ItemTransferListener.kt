package com.example.demo

interface ItemTransferListener {
    fun removeFirstItem(): String?
    fun addItem(item: String)
}