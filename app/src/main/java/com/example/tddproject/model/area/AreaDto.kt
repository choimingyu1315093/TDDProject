package com.example.tddproject.model.area


import com.google.gson.annotations.SerializedName

data class AreaDto(
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("body")
        val body: Body,
        @SerializedName("header")
        val header: Header
    ) {
        data class Body(
            @SerializedName("items")
            val items: Items,
            @SerializedName("numOfRows")
            val numOfRows: Int,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("totalCount")
            val totalCount: Int
        ) {
            data class Items(
                @SerializedName("item")
                val item: List<Item>
            ) {
                data class Item(
                    @SerializedName("code")
                    val code: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("rnum")
                    val rnum: Int
                )
            }
        }

        data class Header(
            @SerializedName("resultCode")
            val resultCode: String,
            @SerializedName("resultMsg")
            val resultMsg: String
        )
    }
}