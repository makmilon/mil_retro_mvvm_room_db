package com.example.mvvmwithhiltwithretrofit.favorite_item

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ProductFav::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDAO(): ProductDao


    companion object {

        val migration1to2 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE product ADD COLUMN price REAL NOT NULL DEFAULT 0.0")
            }
        }

        private var dbINSTANCE: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase{
            if(dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext, AppDatabase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .addMigrations(migration1to2)
                    .build()
            }
            return dbINSTANCE!!
        }
    }
}
