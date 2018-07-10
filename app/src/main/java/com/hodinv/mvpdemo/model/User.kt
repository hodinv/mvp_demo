package com.hodinv.mvpdemo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0,
        var name: String = "",
        var email: String = "",
        var phoneNumber: String = "",
        var assessmentGrade: Char = ' '
)

/*
name, email, phone number and assessment grade (from A to F)
 */