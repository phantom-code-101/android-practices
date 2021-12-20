package com.demo.mysorting.extensions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type
import java.nio.charset.Charset

inline fun <reified T> Context.jsonFromAsset(
    filePath: String,
    typeToken: Type? = null
): T? {
    return resources.assets.open(filePath).use { inputStream ->
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        String(buffer, Charset.forName("UTF-8"))
    }.let { json ->
        try {
            Gson().fromJson(json, typeToken) as T
        }catch (e: JsonSyntaxException){
            null
        }
    }
}