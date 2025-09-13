package com.example.tddproject.model.area

import com.example.tddproject.BuildConfig
import com.example.tddproject.model.common.ErrorHandler

class AreaRepositoryImpl(
    private val api: AreaApiService,
    private val mapper: AreaMapper
): AreaRepository {
    override suspend fun getAreaCode(os: String, app: String, serviceKey: String): List<Area> {
        try {
            val dtos = api.getAreaCode(mobileOS = "AND", mobileApp = "APP", serviceKey = BuildConfig.SERVICE_KEY)
            return mapper.dtoToList(dtos)
        }catch (t: Throwable){
            throw ErrorHandler.wrap(t)
        }
    }
}