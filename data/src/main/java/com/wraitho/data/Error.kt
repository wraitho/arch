package com.wraitho.data

data class Error(val code: String,         // the http status code of the error
                 val message: String)    // a description of the error
