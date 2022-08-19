package com.iamhessam.jsonplaceholder.utils.extra.mapper

interface Mapper<E, D> {

    fun mapEntityToDto(input: E): D
    fun mapDtoToEntity(input: D): E

    fun mapEntityListToDtoList(inputs: List<E>): List<D> {
        return inputs.map { mapEntityToDto(it) }
    }
    fun mapDtoListToEntityList(inputs: List<D>): List<E> {
        return inputs.map { mapDtoToEntity(it) }
    }
}