package com.prestamype.login.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class LoginResponse(
    var status: Int,
    var message: String = "",
    var data: Data = Data()
) {
    @Parcelize
    class Data(var token: String = "", @SerializedName("user_id") var userId: Int = 0) : Parcelable
}