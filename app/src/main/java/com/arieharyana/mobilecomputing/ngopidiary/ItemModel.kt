package com.arieharyana.mobilecomputing.ngopidiary

import java.io.Serializable
import java.util.ArrayList

class ItemModel : Serializable {

//    var title: String? = null
//    var link: String? = null
//    var media: Media? = null
//    var date_taken: String? = null
//    var description: String? = null
//    var published: String? = null
//    var author: String? = null
//    var tags: String? = null
//
//    class Media : Serializable {
//        var m: String? = null
//    }

    var tags: Array<String>? = null
    var link: String? = null
    var images: Image? = null
    var created_time: String? = null
    var caption: Caption? = null
    var user: User? = null
    var username: String? = null
    var profile_pricture: String? = null
    var full_name: String? = null
    var bio: String? = null
    var website: String? = null

    class Image : Serializable {
        var standard_resolution: ImageArr? = null
    }

    class ImageArr : Serializable {
        var url: String? = null
    }

    class Caption : Serializable {
        var text: String? = null
    }

    class User : Serializable {
        var full_name: String? = null
    }
//
//    class Count : Serializable {
//        var media: String? = null
//        var follows: String? = null
//        var followed_by: String? = null
//    }

}