package com.wraitho.data

data class Text(val type: String,       // The string description of the text object (e.g. solicit text, preview text, etc.).
                val language: String,   // A language code denoting which language the text object is written in.
                val text: String)       // The text of the text object.

