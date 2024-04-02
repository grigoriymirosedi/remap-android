package com.example.database.core.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeAdapter(): JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    override fun serialize(time: LocalTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(time?.format(DateTimeFormatter.ISO_LOCAL_TIME))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalTime {
        return LocalTime.parse(json?.asString, DateTimeFormatter.ISO_LOCAL_TIME)
    }
}