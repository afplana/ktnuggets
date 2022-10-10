@file:Suppress("unused")

package io.owndev.ktnuggets.extension

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

/**
* Specially useful for deserializing Lists & dates written as timestamps
* */
inline fun <reified T> Gson.fromJson(json: JsonElement): T = fromJson<T>(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJson(json: String): T = fromJson<T>(json, object : TypeToken<T>() {}.type)
/********************************************************************************************************/
