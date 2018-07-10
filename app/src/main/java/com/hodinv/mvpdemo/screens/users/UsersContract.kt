package com.hodinv.mvpdemo.screens.users

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.MvpPresenter
import com.hodinv.mvpdemo.mvp.MvpRouter
import com.hodinv.mvpdemo.mvp.MvpView

interface UsersContract {
    interface View: MvpView {
        fun setUsers(users: List<User>)
    }
    interface Router: MvpRouter {
        fun openDetail(user: User)
        fun openNewUser()
    }
    interface Presenter: MvpPresenter<View, Router> {
        fun addNewUser()
        fun deleteUser(user: User)
        fun openUserDetail(user: User)
    }
}