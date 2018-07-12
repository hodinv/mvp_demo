package com.hodinv.mvpdemo.screens.detail

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.MvpPresenter
import com.hodinv.mvpdemo.mvp.MvpRouter
import com.hodinv.mvpdemo.mvp.MvpView

interface DetailContract {
    interface View: MvpView {
        enum class Fields {
            Name, Email, Phone, Grade
        }

        enum class ErrorType {
            Empty, Invalid
        }

        fun setName(name: String)
        fun setEmail(email: String)
        fun setPhone(phone: String)
        fun setGrade(grade: Char)
        fun setError(field: Fields, error: ErrorType)
        fun clearError(field: Fields)
    }
    interface Router: MvpRouter {
        fun goBack()
    }
    interface Presenter: MvpPresenter<View, Router> {
        fun setName(name: String)
        fun setEmail(email: String)
        fun setPhone(phone: String)
        fun setGrade(grade: Char)
        fun save()
    }
}