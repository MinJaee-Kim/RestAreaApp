package com.minjaee.restareaapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAreaSerivceTest {
    private lateinit var restAreaService:RestAreaService
    private lateinit var directionService: DirectionService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        directionService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionService::class.java)
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getKeyWordSearch_sendRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("searchresponse.json")
            val responseBody = directionService.getDirection("","").body()
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            println(request.path)
            if (responseBody != null) {
                println(responseBody.currentDateTime)
            }
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}