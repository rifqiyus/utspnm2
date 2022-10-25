package com.a203110015.rifqi_yusdianto_uts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.a203110015.rifqi_yusdianto_uts.db.RoomAppDb
import com.a203110015.rifqi_yusdianto_uts.db.UserEntity

// omponen Arsitektur memberikan class helper ViewModel bagi pengontrol UI yang bertanggung jawab untuk menyediakan data bagi UI. Objek ViewModel otomatis disimpan pada
// saat perubahan konfigurasi sehingga data yang disimpan segera tersedia untuk instance aktivitas
class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    //
    lateinit var allUsers : MutableLiveData<List<UserEntity>>

    //
    init{
        allUsers = MutableLiveData()
        getAllUsers()
    }

    // mengambil data user
    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    //
    fun getAllUsers() {
        val userDao = RoomAppDb.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()

        allUsers.postValue(list)
    }

    // menyisipakan data baru
    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    // update data baru
    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }

    // hapus user
    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }
}