package com.plcoding.tracker_data.repository

import com.google.common.truth.Truth.assertThat
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_data.remote.malformedFoodResponse
import com.plcoding.tracker_data.remote.validFoodResponse
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class TrackerRepositoryImplTest {

	private lateinit var repository: TrackerRepositoryImpl
	private lateinit var mockWebServer: MockWebServer
	private lateinit var okHttpClient: OkHttpClient
	private lateinit var api: OpenFoodApi

	@Before
	fun setUp() {
		mockWebServer = MockWebServer()
		okHttpClient = OkHttpClient.Builder()
			.writeTimeout(1, TimeUnit.SECONDS)
			.readTimeout(1, TimeUnit.SECONDS)
			.connectTimeout(1, TimeUnit.SECONDS)
			.build()
		api = Retrofit.Builder()
			.addConverterFactory(MoshiConverterFactory.create())
			.client(okHttpClient)
			.baseUrl(mockWebServer.url("/"))
			.build()
			.create()

		repository = TrackerRepositoryImpl(
			dao = mockk(relaxed = true),
			api = api
		)
	}

	@After
	fun tearDown() {
		mockWebServer.shutdown()
	}

	@Test
	fun `Search foods, valid response, return results`() = runBlocking {
		mockWebServer.enqueue(
			MockResponse()
				.setResponseCode(200)
				.setBody(validFoodResponse)
		)

		val result = repository.searchFood("banana", 1, 40)

		assertThat(result.isSuccess).isTrue()
	}

	@Test
	fun `Search foods, invalid response, return failure`() = runBlocking {
		mockWebServer.enqueue(
			MockResponse()
				.setResponseCode(403)
				.setBody(validFoodResponse)
		)

		val result = repository.searchFood("banana", 1, 40)

		assertThat(result.isFailure).isTrue()
	}

	@Test
	fun `Search foods, malformed valid response, return failure`() = runBlocking {
		mockWebServer.enqueue(
			MockResponse()
				.setBody(malformedFoodResponse)
		)

		val result = repository.searchFood("banana", 1, 40)

		assertThat(result.isFailure).isTrue()
	}
}