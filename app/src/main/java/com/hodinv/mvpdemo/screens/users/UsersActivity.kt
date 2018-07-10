package com.hodinv.mvpdemo.screens.users

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.Repository
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.BaseMvpActivity
import com.hodinv.mvpdemo.screens.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class UsersActivity : BaseMvpActivity<UsersContract.View, UsersContract.Router, UsersContract.Presenter>(), UsersContract.View, UsersContract.Router {

    val adapter = UsersAdapter({ presenter?.openUserDetail(it) })

    override fun openNewUser() {
        startActivity(DetailActivity.newIntent(this))
    }

    override fun setUsers(users: List<User>) {
        adapter.submitList(users)
    }

    override fun openDetail(user: User) {
        startActivity(DetailActivity.newIntent(this, user))
    }

    override fun createPresenter(): UsersContract.Presenter {
        return UsersPresenter(Repository.instance.usersDao())
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
        list.setHasFixedSize(true)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
        val callback = object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT).or(ItemTouchHelper.START).or(ItemTouchHelper.END)) {

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                if (viewHolder is UserViewHolder) {
                    viewHolder.savedUser?.also { user ->
                        presenter?.deleteUser(user)
                    }
                }
            }

        }
        ItemTouchHelper(callback).attachToRecyclerView(list)
    }
}
