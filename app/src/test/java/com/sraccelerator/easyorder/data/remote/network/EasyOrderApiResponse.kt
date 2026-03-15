package com.sraccelerator.easyorder.data.remote.network

import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class EasyOrderApiNetworkAdapterFactoryTest {
    private val factory = EasyOrderApiNetworkAdapterFactory()
    private val retrofit = mockk<Retrofit>()

    @Test
    fun `given valid parameterized EasyOrderApiResponse when get then return adapter`() {
        val innerType = mockParameterizedType(EasyOrderApiResponse::class.java, String::class.java)
        val returnType = mockParameterizedType(Call::class.java, innerType)

        val adapter = factory.get(returnType, emptyArray(), retrofit)
        assertNotNull(adapter)
    }

    @Test
    fun `given non call return type when get then return null`() {
        val returnType = String::class.java
        val adapter = factory.get(returnType, emptyArray(), retrofit)
        assertNull(adapter)
    }

    private fun mockParameterizedType(raw: Type, vararg args: Type) = object : ParameterizedType {
        override fun getActualTypeArguments(): Array<out Type> = args
        override fun getRawType(): Type = raw
        override fun getOwnerType(): Type? = null
    }
}