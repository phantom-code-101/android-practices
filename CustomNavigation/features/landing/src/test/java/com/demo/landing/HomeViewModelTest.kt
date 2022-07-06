package com.demo.landing

import com.demo.core.utils.FileReader
import com.demo.landing.network.CryptoCurrencyDataSource
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    @MockK
    private val dataSource: CryptoCurrencyDataSource = TODO()

    /**
     * @see : https://www.raywenderlich.com/10091980-testing-rest-apis-using-mockwebserver
     */
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockWebServer.start(8080)
        FileReader.readStringFromFile("")
    }

    @Test
    fun `test_api`() {

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}