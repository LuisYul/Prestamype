package com.papps.consetturcontrol.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Luis Baltodano on 28/07/2020.
 * lbaltodano.peruapps@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
object Util {

    private const val USER_DATE_DEFAULT = "01/01/2000"
    private const val SERVER_DATE_DEFAULT = "2000-01-01"

    fun getCurrentDate(): String =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

    fun toDateUser(date: String?): String {
        if (date.isNullOrEmpty()) return USER_DATE_DEFAULT
        var newDate: String = date
        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val format2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateParsed = format1.parse(date)
            newDate = format2.format(dateParsed)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    fun toDateServer(date: String?): String {
        if (date.isNullOrEmpty()) return SERVER_DATE_DEFAULT
        var newDate: String = date
        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val format2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateParsed = format2.parse(date)
            newDate = format1.format(dateParsed)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    fun getIntValuesFromDateString(date: String?): List<Int> {
        var defList = listOf(2000, 0, 2)
        if (date.isNullOrEmpty()) return defList
        try {
            val values: List<String> = date.split("-")
            val day = values[2].toInt()
            val month = values[1].toInt()
            val year = values[0].toInt()
            defList = listOf(year, month - 1, day)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defList
    }
}