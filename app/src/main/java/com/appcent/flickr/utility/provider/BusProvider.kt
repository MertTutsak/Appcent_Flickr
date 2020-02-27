package com.appcent.flickr.utility.provider

import android.os.Handler
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

class BusProvider {

    companion object {
        private var bus: Bus? = null

        fun getInstance(): Bus {
            if (bus == null) {
                bus = BusProvider().BUS()
            }

            return bus!!
        }

    }

    inner class BUS : Bus(ThreadEnforcer.ANY) {

        fun postDelayed(event: Any, delayMillis: Long) {

            Handler().postDelayed({ post(event) }, delayMillis)
        }
    }
}