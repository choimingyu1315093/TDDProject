package com.example.tddproject.model.area

import com.example.tddproject.utils.NetworkUtil
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    }

    @After
    fun tearDown(){
        server.shutdown()
    }
}