package com.minjaee.restareaapp.data.api

import android.util.Log
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
    private lateinit var restAreaService:RestareaService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        restAreaService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestareaService::class.java)
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
            val responseBody = restAreaService.getKeyWordSearch(37.5591786, 126.9776692, 20000).body()
            val responseList = listOf(responseBody)
            val response = responseList[0]
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            println(response)
//            assertThat(request).isEqualTo("GET /v2/local/search/keyword.json?y=37.5591786&x=126.9776692&radius=20000&query=%EC%B9%B4%ED%8E%98 HTTP/1.1")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}