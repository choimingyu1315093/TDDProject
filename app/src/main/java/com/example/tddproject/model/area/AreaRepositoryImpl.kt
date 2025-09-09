package com.example.tddproject.model.area

class AreaRepositoryImpl(
    private val api: AreaApiService,
    private val mapper: AreaMapper
): AreaRepository {
    override suspend fun getAreaCode(os: String, app: String, type: String, serviceKey: String): List<Area> {
        return emptyList()
    }
}