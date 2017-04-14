package org.reekwest.http.contract

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.reekwest.http.core.Response
import org.reekwest.http.core.Status.Companion.OK
import org.reekwest.http.core.body.bodyString
import org.reekwest.http.core.contract.Body
import org.reekwest.http.core.contract.Header
import org.reekwest.http.core.contract.Query
import org.reekwest.http.core.contract.boolean
import org.reekwest.http.core.contract.int
import org.reekwest.http.core.contract.with
import org.reekwest.http.core.get
import org.reekwest.http.core.header
import org.reekwest.http.core.query

class BindingValuesToMessagesTest {

    private val emptyRequest = get("")

    @Test
    fun `can bind many objects to a request`() {
        val populated = emptyRequest.with(
            Body.string.required() to "the body",
            Header.int().required("intHeader") to 123,
            Query.boolean().required("boolean") to true
        )

        assertThat(populated.bodyString(), equalTo("the body"))
        assertThat(populated.header("intHeader"), equalTo("123"))
        assertThat(populated.query("boolean"), equalTo("true"))
    }

    @Test
    fun `can bind many objects to a response`() {
        val populated = Response(OK).with(
            Body.string.required() to "the body",
            Header.int().required("intHeader") to 123
        )

        assertThat(populated.bodyString(), equalTo("the body"))
        assertThat(populated.header("intHeader"), equalTo("123"))
    }
}

