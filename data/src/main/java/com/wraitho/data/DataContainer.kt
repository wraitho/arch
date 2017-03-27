package com.wraitho.data

data class DataContainer<T>(val offset:	Int,	    // The requested offset (skipped results) of the call
                            val limit: Int,		    // The requested result limit
                            val total: Int,		    // The total number of results available
                            val count: Int,		    // The total number of results returned by this call
                            val results: List<T>)   // The list of entities returned by the call
