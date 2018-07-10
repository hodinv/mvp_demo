package com.hodinv.mvpdemo.screens.detail

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.model.UsersDao
import com.hodinv.mvpdemo.mvp.BaseMvpPresenter

class DetailPresenter(userId: Int?, val userDao: UsersDao) : BaseMvpPresenter<DetailContract.View, DetailContract.Router>(), DetailContract.Presenter {

    val newUser: User

    init {
        if (userId != null) {
            newUser = userDao.getById(userId) ?: User()
        } else {
            newUser = User()
        }
    }

    override fun setName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEmail(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPhone(phone: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setGrade(grade: Char) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}