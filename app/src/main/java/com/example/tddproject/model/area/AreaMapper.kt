package com.example.tddproject.model.area

class AreaMapper {
    fun dtoToModel(areaDto: AreaDto): Area {
        val code = areaDto.code ?: -1
        val name = areaDto.name ?: ""
        return Area(code, name)
    }
}