package com.iamhessam.jsonplaceholder.data.remote.http.ktor.router

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/post")
class Post {

    @Serializable
    @Resource("{id}")
    class Id(val parent: Post = Post(), val id: Long) {

        @Serializable
        @Resource("comments")
        class Comments(val parent: Id)
    }
}