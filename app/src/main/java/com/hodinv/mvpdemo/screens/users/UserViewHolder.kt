package com.hodinv.mvpdemo.screens.users

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hodinv.mvpdemo.model.User
import kotlinx.android.synthetic.main.listitem_user.view.*

class UserViewHolder(val view: View, onDetail: (user: User) -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            savedUser?.also {
                onDetail(it)
            }
        }
    }

    var savedUser: User? = null

    fun setUser(user: User) {
        savedUser = user
        view.name.text = user.name
        view.email.text = user.email
        view.phone.text = user.phoneNumber
        view.grade.text = user.assessmentGrade.toString()
    }

}