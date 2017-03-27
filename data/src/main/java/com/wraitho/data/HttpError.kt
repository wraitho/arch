package com.wraitho.data

data class HttpError(val code: Int,         // the http status code of the error
                     val status: String)    // a description of the error
