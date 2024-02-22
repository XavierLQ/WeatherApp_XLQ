package com.assesment_project.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class LocalNames(
    @SerializedName("be")
    val be: String?,
    @SerializedName("cy")
    val cy: String?,
    @SerializedName("en")
    val en: String?,
    @SerializedName("fr")
    val fr: String?,
    @SerializedName("he")
    val he: String?,
    @SerializedName("ko")
    val ko: String?,
    @SerializedName("mk")
    val mk: String?,
    @SerializedName("ru")
    val ru: String?
)