package com.awra.stud.testtaskapp

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.MODE_PRIVATE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log

fun Context.isConnect(): Boolean {
    "isConnect()".log()
    val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        cm.getNetworkCapabilities(cm.activeNetwork)?.let {
            return when {
                it.hasTransport(TRANSPORT_CELLULAR) -> true
                it.hasTransport(TRANSPORT_WIFI) -> true
                it.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: return false
    } else {
        return cm.activeNetworkInfo?.isAvailable == true
    }
}

/**
 * @return true- run WebView, false - run game
 */
fun Context.getRunMode(): Boolean {
    val sp = getSharedPreferences(NAME_SP, MODE_PRIVATE)
    if (sp.contains(NAME_PROPERTY))
        return sp.getBoolean(NAME_PROPERTY, true)
    else
        return isConnect().also { sp.edit().putBoolean(NAME_PROPERTY, it).apply() }
}

fun String.log() {
    Log.d("Logger", this)
}

const val NAME_PROPERTY = "NAME_PROPERTY"
const val NAME_SP = "data"