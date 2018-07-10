package com.hodinv.mvpdemo.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface UsersDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun addNew(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    fun deleteById(id: Int)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Int): User?
}