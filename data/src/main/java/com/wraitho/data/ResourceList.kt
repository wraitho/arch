package com.wraitho.data

data class ResourceList(val available: Int,             // The number of total available resources in this list
                           val returned: Int,           // The number of resources returned in this resource list (up to 20).
                           val collectionURI: String,   // The path to the list of full view representations of the items in this resource list.
                           val items: List<Resource>)   // A list of summary views of the items in this resource list.
