package com.example.questtechtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostList(@Expose @SerializedName("id")
                    val id: Int? = 0,
                    @Expose @SerializedName("title")
                    val title: String? = "",
                    @Expose @SerializedName("body")
                    val body: String? = "",
                    @Expose @SerializedName("userId")
                    val userId: Int? = 0,
                    @Expose @SerializedName("isSelected")
                    var isSelected: Boolean? = false):java.io.Serializable