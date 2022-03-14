package com.example.jetpackarchitectureassignemt

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Util {
   companion object{
       const val tagApi="story"
       const val apiSubUrl="search_by_date"
       const val apiQueryTagArg="tags"
       const val apiQueryPageArg="page"
       const val dataFormat="MMM d,yyyy"
       const val dataBase="Student Database"
       const val selectYear="Select Year"
       const val yearFormat="yyyy"
       const val dateOldFormat="yyyy-MM-dd'T'hh:mm:ss"
   }
     fun checkForInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
    fun setFragment(fragmentId:Int,ctx:Context,fragment:Fragment) {
        val setFragmentManager = (ctx as FragmentActivity).supportFragmentManager
        val fragmentTransaction = setFragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentId, fragment)
        if (setFragmentManager.fragments.isNotEmpty()) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    fun setNestedFragment(fragmentId:Int,ctx:Context,fragment:Fragment) {
        val setFragmentManager = (ctx as FragmentActivity).supportFragmentManager
        val fragmentTransaction = setFragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentId, fragment)
        fragmentTransaction.commit()
    }


    fun dateConverter(date:String):String{
        val originalFormat: DateFormat = SimpleDateFormat(dateOldFormat, Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat(dataFormat, Locale.US)
        val origFormatDate = originalFormat.parse(date)
        return targetFormat.format(origFormatDate ?: "")
    }
}