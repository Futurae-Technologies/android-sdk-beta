package com.futurae.futuraedemo

import com.futurae.sdk.public_api.exception.FTSignatureException
import org.junit.Assert.assertEquals
import org.junit.Test


class TestingTests {
    @Test
    fun `sample test`() {
        val exception = FTSignatureException("msg", Throwable())
        val expected = 4
        assertEquals(expected, 2 + 2)
        assertEquals("msg", exception.message)
    }
}