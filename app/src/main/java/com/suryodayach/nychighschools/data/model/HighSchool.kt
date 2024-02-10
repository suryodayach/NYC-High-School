package com.suryodayach.nychighschools.data.model

import com.google.gson.annotations.SerializedName

data class HighSchool(
    val dbn: String,

    @SerializedName("school_name")
    val schoolName: String,

    @SerializedName("overview_paragraph")
    val overview: String,

    val location: String = "",

    @SerializedName("phone_number")
    val phoneNumber: String = "NA",

    @SerializedName("school_email")
    val schoolEmail: String = "NA",

    val city: String = "",
)
