package com.zero.myapplication.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zero.myapplication.ui.no_connection.NoConnectionFragment
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.myapplication.data.model.login.ResponseLogin
import com.zero.myapplication.databinding.ActivityLoginBinding
import com.zero.myapplication.root.App
import com.zero.myapplication.ui.main.MainActivity
import com.zero.myapplication.utils.SimpleState
import com.zero.myapplication.utils.Validation.validateEditText
import dmax.dialog.SpotsDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModel()
    private val spotsDialog: SpotsDialog by lazy {
        SpotsDialog(this, "Mohon tunggu...")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()

        binding.btnLogin.setOnClickListener {
            if (validateEditText(listOf(binding.etUsername, binding.etPassword))) login()
        }

    }

    private fun login() {
        viewModel.postLogin(binding.etUsername.text.toString(), binding.etPassword.text.toString())
    }

    private fun initListener() {
        viewModel.stateLogin.observe(this) { state ->
            state.let {
                when (it) {
                    is SimpleState.Loading -> {
                        spotsDialog.show()
                    }
                    is SimpleState.Result<*> -> {
                        spotsDialog.dismiss()
                        if (it.data is ResponseLogin) {
                            if (it.data.success == 1) {
                                App.sessions.doLogin(it.data.data!![0])
                                startActivity(MainActivity::class.java)
                                finish()
                            } else {
                                toast(it.data.message!!)
                            }
                        }
                    }
                    is SimpleState.Error -> {
                        spotsDialog.dismiss()
                        App.makeToast.toastThrowable(it.error)
                        NoConnectionFragment.newInstance { login() }
                            .show(supportFragmentManager, "Bottom No Connection")
                    }
                }
            }

        }
    }

}