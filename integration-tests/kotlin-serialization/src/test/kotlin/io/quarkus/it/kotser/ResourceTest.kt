package io.quarkus.it.kotser

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class ResourceTest {
    @Test
    fun testGet() {
        given()
            .`when`().get("/")
            .then()
            .statusCode(200)
            .body(`is`(
                """
                    {
                      "name": "Jim Halpert",
                      "defaulted": "hi there!"
                    }""".trimIndent()
            ))
    }

    @Test
    fun testSuspendGet() {
        given()
            .`when`().get("/suspend")
            .then()
            .statusCode(200)
            .body(`is`(
                """
                    {
                      "name": "Jim Halpert",
                      "defaulted": "hi there!"
                    }""".trimIndent()
            ))
    }

    @Test
    fun testSuspendGetList() {
        given()
            .`when`().get("/suspendList")
            .then()
            .statusCode(200)
            .body(`is`(
                """
[
  {
    "name": "Jim Halpert",
    "defaulted": "hi there!"
  }
]
""".trimIndent()
            ))
    }

    @Test
    fun testPost() {
        given()
            .body("{\"name\":\"Pam Beasley\"}")
            .contentType(JSON)
            .`when`().post("/")
            .then()
            .statusCode(200)
            .body(`is`("""
                {
                  "name": "Pam Halpert",
                  "defaulted": "hi there!"
                }""".trimIndent()))
    }
}
