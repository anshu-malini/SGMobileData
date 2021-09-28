package com.am.sgmobiledata.utils

import android.util.Log

class TrackingClass {
    companion object {
        fun setCurrentScreen(screenName: String) {
            Log.e(TAG_NAME, "Currently viewing: $screenName")
        }
    }
}