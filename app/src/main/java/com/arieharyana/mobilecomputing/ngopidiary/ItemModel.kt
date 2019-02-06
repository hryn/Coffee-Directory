package com.arieharyana.mobilecomputing.ngopidiary

import java.io.Serializable
import java.util.ArrayList

class ItemModel : Serializable {

    var tags: Array<String>? = null
    var link: String? = null
    var images: Image? = null
    var created_time: String? = null
    var caption: Caption? = null
    var user: User? = null
    var username: String? = null
    var profile_picture: String? = null
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

}