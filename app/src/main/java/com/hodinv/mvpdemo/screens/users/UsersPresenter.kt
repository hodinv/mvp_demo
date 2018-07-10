package com.hodinv.mvpdemo.screens.users

import android.util.Log
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.model.UsersDao
import com.hodinv.mvpdemo.mvp.BaseMvpPresenter

class UsersPresenter(val dao: UsersDao): BaseMvpPresenter<UsersContract.View, UsersContract.Router>(), UsersContract.Presenter {
    override fun deleteUser(user: User) {
        dao.deleteById(user.id)
        view?.setUsers(dao.getAll())
    }

    override fun openUserDetail(user: User) {
        router?.openDetail(user)
    }

    override fun addNewUser() {
        router?.openNewUser()
    }

    override fun onStart() {
        super.onStart()
        view?.setUsers(dao.getAll())
    }
}