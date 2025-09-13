package com.example.tddproject.model.area

data class AreaDto(
    val response: Response?
) {
    data class Response(
        val body: Body?,
        val header: Header?
    ) {
        data class Body(
            val items: Items?,
            val numOfRows: Int,
            val pageNo: Int,
            val totalCount: Int
        ) {
            data class Items(
                val item: List<Item>?
            ) {
                data class Item(
                    val code: String? = null,
                    val name: String? = null,
                    val rnum: Int? = null
                )
            }
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}