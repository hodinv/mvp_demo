package com.hodinv.mvpdemo.screens.detail

import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.model.UsersDao
import com.hodinv.mvpdemo.screens.users.UsersContract
import com.hodinv.mvpdemo.screens.users.UsersPresenter
import org.junit.Before
import org.mockito.Mockito

class DetailPresenterTest {
    lateinit var presenter: DetailPresenter
    lateinit var dao: UsersDao
    lateinit var view: DetailContract.View
    lateinit var router: DetailContract.Router

    val user = User(1, "User1", "email1@mail.com", "11111", 'A')


    @Before
    fun prepare() {
        dao = Mockito.mock(UsersDao::class.java)
        view = Mockito.mock(DetailContract.View::class.java)
        router = Mockito.mock(DetailContract.Router::class.java)
        presenter = DetailPresenter(1, dao)
        presenter.view = view
        presenter.router = router
    }
}
