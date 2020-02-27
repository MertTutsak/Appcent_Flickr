package com.appcent.flickr.utility

object Constants {

    object App {
        val TIMESTAMP_FORMAT = "dd/MM/yyyy-HH:mm:ss"
        internal val PREF_NAME = "flickr-appcent-pref"

    }

    object API {
        val RESPONSE_FORMAT = "json"
        val NO_JSON_CALLBACK = 1

        val GET_RECENT_METHOD = "flickr.photos.getRecent"
    }

    object SHARE_ELEMENT{
        val SHARE_IMAGE = "image"
        val SHARE_IMAGE_TRANSITION_NAME = "image_transition"
    }


    /**
     * -	original size
     * s	small square 75x75
     * q	large square 150x150
     * m	small, 240 on longest side
     * n	small, 320 on longest side
     * b	large, 1024 on longest side*
     * z	medium 640, 640 on longest side
     * c	medium 800, 800 on longest side†
     */
    object PHOTO {
        val ORIGINAL_SIZE = ""
        val SQUARE_75_SIZE = "_s"
        val SQUARE_150_SIZE = "_q"
        val SMALL_240_SIZE = "_m"
        val SMALL_320_SIZE = "_n"
        val LARGE_1024_SIZE = "_b"
        val MEDIUM_640_SIZE = "_z"
        val MEDIUM_800_SIZE = "_c"
    }
}