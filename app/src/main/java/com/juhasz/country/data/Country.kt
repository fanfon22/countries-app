package com.juhasz.country.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class Country (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "country") var country: String?,
    @ColumnInfo(name = "region") var region: String?,
    @ColumnInfo(name = "email") var email: String?,
         )
