package com.hodinv.mvpdemo.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(version = 1, entities = [(User::class)], exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}