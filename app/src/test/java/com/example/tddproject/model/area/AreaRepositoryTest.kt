package com.example.tddproject.model.area

import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat

class AreaRepositoryTest {

    private lateinit var server: MockWebServer
    private lateinit var api: AreaApiService
    private lateinit var repo: AreaRepository
    private lateinit var mapper: AreaMapper

    @Before
    fun setup(){
        server = MockWebServer().apply{ start() }

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AreaApiService::class.java)
        mapper = AreaMapper()
        repo = AreaRepositoryImpl(api, mapper)
    }

    @Test
    fun `성공 - 올바른 쿼리와 매핑`() = runTest {
        val body = """
            {
              "response": {
                "body": {
                  "items": {
                    "item": [
                      {
                        "rnum": 1,
                        "code": "1",
                        "name": "서울"
                      }
                    ]
                  }
                }
              }
            }
        """.trimIndent()

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(body)
        )

        // then: 매핑 검증
        val list = repo.getAreaCode("AND", "APP", "SERVICE_KEY")
        assertThat(list).containsExactly(
            Area(1, "서울"),
        ).inOrder()

        // then: 전송된 요청의 쿼리 확인
        val req = server.takeRequest()
        val url = req.requestUrl!!
        assertThat(req.method).isEqualTo("GET")
        assertThat(url.encodedPath).isEqualTo("/B551011/KorService2/areaCode2")
        assertThat(url.queryParameter("MobileOS")).isEqualTo("AND")
        assertThat(url.queryParameter("MobileApp")).isEqualTo("APP")
        assertThat(url.queryParameter("_type")).isEqualTo("json")
        assertThat(url.queryParameter("serviceKey")).isEqualTo("SERVICE_KEY")
    }

    @After
    fun tearDown(){
        server.shutdown()
    }
}