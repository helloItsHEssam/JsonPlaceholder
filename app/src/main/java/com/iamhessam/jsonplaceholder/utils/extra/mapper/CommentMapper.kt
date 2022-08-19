package com.iamhessam.jsonplaceholder.utils.extra.mapper

import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.dto.CommentDTO

class CommentMapper : Mapper<CommentEntity, CommentDTO> {

    override fun mapEntityToDto(input: CommentEntity): CommentDTO {
        return CommentDTO(id = input.id, body = input.comment, postId = -1)
    }

    override fun mapDtoToEntity(input: CommentDTO): CommentEntity {
        return CommentEntity(id = input.id, comment = input.body ?: "")
    }
}