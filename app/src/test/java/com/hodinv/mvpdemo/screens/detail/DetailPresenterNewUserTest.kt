package com.hodinv.mvpdemo.screens.detail

import com.hodinv.mvpdemo.model.UsersDao
import com.hodinv.mvpdemo.screens.users.UsersContract
import com.hodinv.mvpdemo.screens.users.UsersPresenter
import org.junit.Before
import org.mockito.Mockito

class DetailPresenterNewUserTest {
    lateinit var presenter: DetailPresenter
    lateinit var dao: UsersDao
    lateinit var view: DetailContract.View
    lateinit var router: DetailContract.Router

    @Before
    fun prepare() {
        dao = Mockito.mock(UsersDao::class.java)
        view = Mockito.mock(DetailContract.View::class.java)
        router = Mockito.mock(DetailContract.Router::class.java)
        presenter = DetailPresenter(null, dao)
        presenter.view = view
        presenter.router = router
    }
}
