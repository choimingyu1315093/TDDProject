package com.example.tddproject.model.area

class AreaMapper {
    fun dtoToList(areaDto: AreaDto): List<Area> {
        val items = areaDto.response?.body?.items?.item.orEmpty()
        return items.mapNotNull {
            val code = it.code?.toIntOrNull() ?: return@mapNotNull null //it.code가 숫자로 변환 가능하면 int 로 저장하고, 아니면 리스트에서 빼라
            val name = it.name?.takeIf { it.isNotBlank() } ?: return@mapNotNull null //it.name이 빈 문자열이거나 null이면 빼라.
            Area(code, name)
        }
    }
}