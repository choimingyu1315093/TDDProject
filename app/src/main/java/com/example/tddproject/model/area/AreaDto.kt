package com.example.tddproject.model.area

import com.google.gson.annotations.SerializedName

data class AreaDto(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("name")
    val name: String? = null
)
