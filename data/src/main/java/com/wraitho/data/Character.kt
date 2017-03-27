package com.wraitho.data

import java.util.Date

data class Character(val id: Int,				//	The unique ID of the character resource.
                     val name: String,			//	The name of the character.
                     val description: String,	//	A short bio or description of the character.
                     val modified: Date,		//	The date the resource was most recently modified.
                     val resourceURI: String,	//	The canonical URL identifier for this resource.
                     val urls: List<Url>,		// 	A set of public web site URLs for the resource.
                     val thumbnail: Image,		//	The representative image for this character.
                     val comics: ResourceList,	//	A resource list containing comics which feature this character.
                     val stories: ResourceList,	//	A resource list of stories in which this character appears.
                     val events: ResourceList,	//	A resource list of events in which this character appears.
                     val series: ResourceList)	//	A resource list of series in which this character appears.
