package com.iamhessam.jsonplaceholder.utils.extra

interface Mapper<E, D> {

    fun mapEntityToDomain(input: E): D
    fun mapDomainToEntity(input: D): E

    fun mapEntityListToDomainList(inputs: List<E>): List<D> {
        return inputs.map { mapEntityToDomain(it) }
    }
    fun mapDomainListToEntityList(inputs: List<D>): List<E> {
        return inputs.map { mapDomainToEntity(it) }
    }
}