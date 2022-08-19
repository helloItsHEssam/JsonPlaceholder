package com.iamhessam.jsonplaceholder

import com.iamhessam.jsonplaceholder.data.remote.http.ktor.dto.CommentDTO
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.engine.KtorClient
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.router.Post
import com.iamhessam.jsonplaceholder.utils.exception.ArashniaException
import com.iamhessam.jsonplaceholder.utils.extra.mapper.CommentMapper
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.resources.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class KtorUnitTest {

    @Test
    fun testSampleText() {
        runBlocking {
            val mockEngine = MockEngine {
                respond(
                    content = ByteReadChannel("hello"),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = KtorClient()
            apiClient.setEngine(mockEngine)

            val path = Post.Id.Comments(Post.Id(id = 1))
            val response: String = apiClient.httpClient.get(path).body()
            Assert.assertEquals("hello", response)
        }
    }

    @Test(expected = ArashniaException.NotFoundException::class)
    fun testException() {
        runBlocking {
            val mockEngine = MockEngine {
                respond(
                    content = ByteReadChannel("hello"),
                    status = HttpStatusCode.NotFound,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = KtorClient()
            apiClient.setEngine(mockEngine)

            val path = Post.Id.Comments(Post.Id(id = 1))
            apiClient.httpClient.get(path)
        }
    }

    @Test
    fun testParseToModel() {
        runBlocking {
            val mockEngine = MockEngine {
                respond(
                    // sample json Data
                    content = ByteReadChannel("[{\"postId\":1,\"id\":1,\"name\":\"idlaboreexetquamlaborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantiumenimquasiestquidemmagnamvoluptateipsameos\\ntemporaquonecessitatibus\\ndolorquamautemquasi\\nreiciendisetnamsapienteaccusantium\"}]"),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = KtorClient()
            apiClient.setEngine(mockEngine)

            val path = Post.Id.Comments(Post.Id(id = 1))
            val comments: List<CommentDTO> = apiClient.httpClient.get(path).body()

            Assert.assertEquals(1, comments.size)
            Assert.assertEquals("Eliseo@gardner.biz", comments.first().email)

            val entities = CommentMapper().mapDtoListToEntityList(comments)

            Assert.assertEquals(1, entities.size)
            Assert.assertEquals(comments.first().id, entities.first().id)
            Assert.assertEquals(comments.first().body, entities.first().comment)
        }
    }
}