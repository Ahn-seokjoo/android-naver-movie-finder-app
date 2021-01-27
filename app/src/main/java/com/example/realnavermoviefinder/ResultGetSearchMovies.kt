package com.example.realnavermoviefinder

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ResultGetSearchMovies(
    @SerializedName("lastBuildDate") var lastBuildDate: String ,
    @SerializedName("total") var total: Int ,
    @SerializedName("start") var start: Int ,
    @SerializedName("display") var display: Int ,
    @SerializedName("items") var items: List<Items>
) {


    data class Items(
    @SerializedName("title") var title: String = "",
    @SerializedName("link") var link: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("subtitle") var subtitle: String = "",
    @SerializedName("pubDate") var pubDate: String = "",
    @SerializedName("director") var director: String = "",
    @SerializedName("actor") var actor: String = "",
    @SerializedName("userRating") var userRating: Double
) {

}


}
