package com.demo.shared.domain.model.feed

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("by") val from: String?,
    val descendants: Int?,
    val id: Int?,
    val kids: List<Int?>?,
    val score: Int?,
    val time: Int?,
    val title: String?,
    val type: String?,
    val url: String?
)

data class AskStories(
    val ids: List<String>
)


data class TopStories(
    val ids: List<String>
)