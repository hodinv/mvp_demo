package com.hodinv.mvpdemo.screens.users

import android.util.Log
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.BaseMvpPresenter

class UsersPresenter: BaseMvpPresenter<UsersContract.View, UsersContract.Router>(), UsersContract.Presenter {
    override fun deleteUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openUserDetail(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
        Log.d("LC", "UsersPresenter->Started")
    }
}