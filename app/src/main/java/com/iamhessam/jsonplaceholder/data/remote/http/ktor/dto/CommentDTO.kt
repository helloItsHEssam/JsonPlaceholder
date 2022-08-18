package com.iamhessam.jsonplaceholder.data.remote.http.ktor.dto

import kotlinx.serialization.Serializable

@Serializable
data class CommentDTO(
    var postId: Int,
    var id: Int,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
)