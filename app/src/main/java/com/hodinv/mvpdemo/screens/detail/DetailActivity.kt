package com.hodinv.mvpdemo.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.Repository
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.BaseMvpActivity

class DetailActivity : BaseMvpActivity<DetailContract.View, DetailContract.Router, DetailContract.Presenter>() {
    override fun createPresenter(): DetailContract.Presenter {
        val id = intent.extras.getInt(EXTRA_USER_ID)
        return DetailPresenter(
                if (id != NEW_USER) id else null,
                Repository.instance.usersDao()
             )
    }

    override fun getMvpView(): DetailContract.View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRouter(): DetailContract.Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        val EXTRA_USER_ID = "userId"
        val NEW_USER = 0

        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, user.id)
            return intent
        }

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, NEW_USER)
            return intent
        }
    }

}
