package com.hodinv.mvpdemo.screens.detail

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.MvpPresenter
import com.hodinv.mvpdemo.mvp.MvpRouter
import com.hodinv.mvpdemo.mvp.MvpView

interface DetailContract {
    interface View: MvpView {
        fun setName(name: String)
        fun setEmail(email: String)
        fun setPhone(phone: String)
        fun setGrade(grade: Char)
        fun setNameError(invalid: Boolean, message: String = "")
        fun setEmailError(invalid: Boolean, message: String = "")
        fun setPhoneError(invalid: Boolean, message: String = "")
        fun setGradePError(invalid: Boolean, message: String = "")
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