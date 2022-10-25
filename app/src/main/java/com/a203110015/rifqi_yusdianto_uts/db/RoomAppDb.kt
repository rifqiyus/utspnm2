package com.a203110015.rifqi_yusdianto_uts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


/*
Anda menentukan setiap entity Room sebagai class yang dianotasikan dengan @Entity. Entity Room menyertakan kolom untuk setiap kolom dalam tabel yang sesuai di database,
termasuk satu atau beberapa kolom yang membentuk kunci utama.
 */
@Database(entities = [UserEntity::class], version = 2)
abstract class RoomAppDb: RoomDatabase() {


    abstract fun userDao(): UserDao?
//
    companion object {
        private var INSTANCE: RoomAppDb?= null

        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }

    //
        fun getAppDatabase(context: Context): RoomAppDb? {
            if(INSTANCE == null ) {
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDBB"
                )
                    .addMigrations(migration_1_2)
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
//
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}