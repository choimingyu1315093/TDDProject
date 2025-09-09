package com.example.tddproject.model.area

interface AreaRepository {

    suspend fun getAreaCode(os: String, app: String, type: String, serviceKey: String): List<Area>
}