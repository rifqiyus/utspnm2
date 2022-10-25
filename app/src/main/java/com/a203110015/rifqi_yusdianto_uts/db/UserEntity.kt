package com.a203110015.rifqi_yusdianto_uts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// untuk tabel userinfo
@Entity(tableName = "userinfo")
data class UserEntity (
    // kolom pada tabel
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0 ,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String?
)
