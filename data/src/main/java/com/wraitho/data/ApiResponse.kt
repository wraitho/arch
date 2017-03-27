package com.wraitho.data

data class ApiResponse<T>(val code: Int,                // The HTTP status code of the returned result
                          val status: String,           // A string description of the call status
                          val data: DataContainer<T>,                  // A string description of the call status
                          val etag: String,             // A digest value of the content
                          val copyright: String,        // The copyright notice for the returned result
                          val attributionText: String,  // The attribution notice for this result
                          val attributionHTML: String)  // An HTML representation of the attribution notice for this result
