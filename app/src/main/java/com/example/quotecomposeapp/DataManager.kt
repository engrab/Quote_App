package com.example.quotecomposeapp

import android.content.Context
import android.graphics.pdf.PdfDocument
import androidx.compose.runtime.mutableStateOf
import com.example.quotecomposeapp.model.Quote
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()
    var currentQuote: Quote? = null
    var isDataLoaded = mutableStateOf(false)
    var currentPages = mutableStateOf(Pages.LISTING)
    fun loadAssetFromFile(context: Context) {

        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages(quote: Quote?) {
        if (currentPages.value == Pages.LISTING) {
            currentQuote = quote
            currentPages.value = Pages.DETAIL
        } else {
            currentPages.value = Pages.LISTING
        }
    }
}