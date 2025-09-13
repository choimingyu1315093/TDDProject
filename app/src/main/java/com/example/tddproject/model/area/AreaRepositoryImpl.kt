package com.example.tddproject.model.area

import com.example.tddproject.BuildConfig
import com.example.tddproject.model.common.ErrorHandler

class AreaRepositoryImpl(
    private val api: AreaApiService,
    private val mapper: AreaMapper
): AreaRepository {
    override suspend fun getAreaCode(os: String, app: String, serviceKey: String): List<Area> {
        return emptyList()
    }
}