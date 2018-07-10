package com.hodinv.mvpdemo.screens.users

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.User


class UsersAdapter(val onDetail: (user: User)->Unit) : ListAdapter<User, UserViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent?.context).inflate(R.layout.listitem_user, parent, false), onDetail)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setUser(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldUser: User?, newUser: User?): Boolean {
                return oldUser?.id == newUser?.id;
            }

            override fun areContentsTheSame(oldUser: User?, newUser: User?): Boolean {
                return oldUser?.equals(newUser) ?: false
            }

        }
    }
}