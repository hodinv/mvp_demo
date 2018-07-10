package com.hodinv.mvpdemo.model

import android.arch.persistence.room.Room
import android.content.Context

class Repository(context: Context) {

    private var db: DataBase = Room.databaseBuilder(context, DataBase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    fun usersDao() = db.usersDao()

    companion object {
        /**
         * instance of Database provider
         */
        lateinit var instance: Repository
            private set

        /**
         * Initialize instance of database provider. Should be called in Application.onCreate
         */
        fun initialize(context: Context) {
            instance = Repository(context)
        }
    }
}