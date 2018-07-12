package com.hodinv.mvpdemo.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hodinv.mvpdemo.R
import com.hodinv.mvpdemo.model.Repository
import com.hodinv.mvpdemo.model.User
import com.hodinv.mvpdemo.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseMvpActivity<DetailContract.View, DetailContract.Router, DetailContract.Presenter>(), DetailContract.View, DetailContract.Router {

    override fun setError(field: DetailContract.View.Fields, error: DetailContract.View.ErrorType) {
        if (error == DetailContract.View.ErrorType.Empty) {
            when (field) {
                DetailContract.View.Fields.Name -> {
                    errorName.visibility = View.VISIBLE
                    errorName.setText(R.string.error_required_name)
                }
                DetailContract.View.Fields.Email -> {
                    errorEmail.visibility = View.VISIBLE
                    errorEmail.setText(R.string.error_required_email)
                }
                DetailContract.View.Fields.Phone -> {
                    errorPhone.visibility = View.VISIBLE
                    errorPhone.setText(R.string.error_required_phone)
                }
                DetailContract.View.Fields.Grade -> {
                    errorGrade.visibility = View.VISIBLE
                    errorGrade.setText(R.string.error_required_grade)
                }
            }
        }
        if (error == DetailContract.View.ErrorType.Invalid) {
            when (field) {
                DetailContract.View.Fields.Email -> {
                    errorEmail.visibility = View.VISIBLE
                    errorEmail.setText(R.string.error_wrong_email)
                }
            }
        }
    }

    override fun clearError(field: DetailContract.View.Fields) {
        when (field) {
            DetailContract.View.Fields.Name -> errorName.visibility = View.INVISIBLE
            DetailContract.View.Fields.Email -> errorEmail.visibility = View.INVISIBLE
            DetailContract.View.Fields.Phone -> errorPhone.visibility = View.INVISIBLE
            DetailContract.View.Fields.Grade -> errorGrade.visibility = View.INVISIBLE
        }
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun setName(nameValue: String) {
        name.setText(nameValue)
    }

    override fun setEmail(emailValue: String) {
        email.setText(emailValue)
    }

    override fun setPhone(phoneValue: String) {
        phone.setText(phoneValue)
    }

    override fun setGrade(gradeValue: Char) {
        when (gradeValue) {
            'A' -> grade.check(R.id.gradeA)
            'B' -> grade.check(R.id.gradeB)
            'C' -> grade.check(R.id.gradeC)
            'D' -> grade.check(R.id.gradeD)
            'E' -> grade.check(R.id.gradeE)
        }
    }


    override fun createPresenter(): DetailContract.Presenter {
        val id = intent.extras.getInt(EXTRA_USER_ID)
        return DetailPresenter(
                if (id != NEW_USER) id else null,
                Repository.instance.usersDao()
        )
    }

    override fun getMvpView(): DetailContract.View {
        return this
    }

    override fun getRouter(): DetailContract.Router {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        commit.setOnClickListener {
            presenter?.setName(name.text.toString())
            presenter?.setEmail(email.text.toString())
            presenter?.setPhone(phone.text.toString())
            presenter?.save()
        }

        gradeA.setOnClickListener { presenter?.setGrade('A') }
        gradeB.setOnClickListener { presenter?.setGrade('B') }
        gradeC.setOnClickListener { presenter?.setGrade('C') }
        gradeD.setOnClickListener { presenter?.setGrade('D') }
        gradeE.setOnClickListener { presenter?.setGrade('E') }

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
