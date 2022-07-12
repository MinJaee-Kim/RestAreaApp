package com.minjaee.restareaapp.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.minjaee.restareaapp.data.model.keywordsearch.Documents
import com.minjaee.restareaapp.data.model.keywordsearch.Meta

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromDocumentJson(documents: List<Documents>):String = Gson().toJson(documents)
    @TypeConverter
    fun documentsToJson(documents: String) = Gson().fromJson(documents, Array<Documents>::class.java).toList()
    @TypeConverter
    fun fromMetaJson(meta: Meta):String = Gson().toJson(meta)
    @TypeConverter
    fun metaToJson(meta: String) = Gson().fromJson(meta, Meta::class.java)
}