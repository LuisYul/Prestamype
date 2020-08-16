package com.prestamype.login.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(val token: String, val userId: Int) : Parcelable