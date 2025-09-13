package com.example.tddproject.model.area

interface AreaRepository {

    suspend fun getAreaCode(os: String, app: String, serviceKey: String): List<Area>
}