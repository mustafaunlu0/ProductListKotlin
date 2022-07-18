package com.mustafaunlu.productlistkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Product(

    @ColumnInfo(name="name")
    @SerializedName("Maker")
    val maker : String?,

    @ColumnInfo(name="detail")
    @SerializedName("Title")
    val title: String?,

    @ColumnInfo(name="Description")
    @SerializedName("Description")
    val description : String?,

    @ColumnInfo(name = "img")
    @SerializedName("img")
    val imageUrl : String?,


) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int? = 0
}