package br.ac.algorithms

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class StringAlgorithmsTest {

    @Test
    fun `Teste reverse string`() {
        assertAll(
            { assertEquals("xela", reverse("alex")) },
            { assertEquals("321nrael", reverse("learn123")) }
        )
    }

    @Test
    fun `Test reverse only alphabetic chars`() {
        assertAll(
            { assertEquals("xela", reverseOnlyAlphabeticChars("alex")) },
            { assertEquals("nrael123", reverseOnlyAlphabeticChars("learn123")) },
            { assertEquals("r,e\\$,cba", reverseOnlyAlphabeticChars("a,b\\$,cer"))}
        )
    }
}