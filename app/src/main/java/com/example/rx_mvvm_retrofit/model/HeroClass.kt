package com.example.rx_mvvm_retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HeroClass {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("realname")
    @Expose
    var realname: String? = null

    @SerializedName("team")
    @Expose
    var team: String? = null

    @SerializedName("firstappearance")
    @Expose
    var firstappearance: String? = null

    @SerializedName("createdby")
    @Expose
    var createdby: String? = null

    @SerializedName("publisher")
    @Expose
    var publisher: String? = null

    @SerializedName("imageurl")
    @Expose
    var imageurl: String? = null

    @SerializedName("bio")
    @Expose
    var bio: String? = null

}