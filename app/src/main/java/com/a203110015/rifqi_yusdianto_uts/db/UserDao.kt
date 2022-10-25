package com.a203110015.rifqi_yusdianto_uts.db

import androidx.room.*
/*
Anda dapat menentukan setiap DAO sebagai antarmuka atau class abstrak. Untuk kasus penggunaan dasar,
Anda biasanya harus menggunakan antarmuka. Dalam kedua kasus tersebut, Anda harus selalu menganotasi DAO dengan @Dao.
DAO tidak memiliki properti, tetapi menentukan satu atau beberapa metode untuk berinteraksi dengan data di database aplikasi Anda.
 */
@Dao
interface UserDao {

    // query untuk mengambil data userinfo
    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>?

    // fungsi insert data baru user
    @Insert
    fun insertUser(user: UserEntity?)

    // fungsi delete user
    @Delete
    fun deleteUser(user: UserEntity?)

    // fungsi update user
    @Update
    fun updateUser(user: UserEntity?)

}