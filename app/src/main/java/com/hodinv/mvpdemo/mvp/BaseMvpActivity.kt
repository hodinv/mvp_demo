package com.hodinv.mvpdemo.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseMvpActivity<V : MvpView, Router : MvpRouter, P : MvpPresenter<V, Router>> : AppCompatActivity() {

    var presenter: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (presenter == null) {
            // incase we alrady mock presnter we do not need to recreate it
            presenter = createPresenter()
        }
    }

    abstract fun createPresenter(): P

    /**
     * Return view for this fragment, usually - fragment itself
     * @return View
     */
    abstract fun getMvpView(): V

    /**
     * Return router for this fragment, usually - activity that holds this fragment
     */
    abstract fun getRouter(): Router

    override fun onStart() {
        super.onStart()
        presenter?.view = getMvpView()
        presenter?.router = getRouter()
        presenter?.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
        presenter?.view = null
        presenter?.router = null
    }
}