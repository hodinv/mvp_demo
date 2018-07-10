package com.hodinv.mvpdemo.screens.users

import android.os.Bundle
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class UsersActivity : BaseMvpActivity<UsersContract.View, UsersContract.Router, UsersContract.Presenter>(), UsersContract.View, UsersContract.Router {
    override fun openNewUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUsers(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openDetail(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): UsersContract.Presenter {
        return UsersPresenter()
    }


    override fun getMvpView(): UsersContract.View {
        return this
    }

    override fun getRouter(): UsersContract.Router {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addNewUser.setOnClickListener { presenter?.addNewUser() }
    }
}
