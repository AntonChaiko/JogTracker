package com.example.data.models

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    @SerializedName("text")
    val text: String,
    @SerializedName("topic_id")
    val topicId: Int
)