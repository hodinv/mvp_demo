package com.hodinv.mvpdemo.screens.users

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.model.UsersDao
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UsersPresenterTest {

    lateinit var presenter: UsersPresenter
    lateinit var dao: UsersDao
    lateinit var view: UsersContract.View
    lateinit var router: UsersContract.Router

    val user = User(1, "User1", "email1@mail.com", "11111", 'A')

    @Before
    fun prepare() {
        dao = Mockito.mock(UsersDao::class.java)
        view = Mockito.mock(UsersContract.View::class.java)
        router = Mockito.mock(UsersContract.Router::class.java)
        presenter = UsersPresenter(dao)
        presenter.view = view
        presenter.router = router
    }

    @Test
    fun testSetItemsOnStart() {
        Mockito.`when`(dao.getAll()).thenReturn(emptyList())
        presenter.onStart()
        Mockito.verify(view).setUsers(emptyList())

    }

    @Test
    fun testDeleteUser() {
        presenter.deleteUser(user)
        Mockito.verify(dao).deleteById(user.id)
    }

    @Test
    fun testOpenUserDetail() {
        presenter.openUserDetail(user)
        Mockito.verify(router).openDetail(user)
    }

    @Test
    fun testAddNewUser() {
        presenter.addNewUser()
        Mockito.verify(router).openNewUser()
    }

}