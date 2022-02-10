package com.zero.myapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.zero.myapplication.data.network.ApiEndpoint
import com.zero.myapplication.utils.NetworkHelper
import com.zero.myapplication.utils.SimpleState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val endpoint: ApiEndpoint, private val networkHelper: NetworkHelper
) :
    ViewModel() {

    val stateLogin: MutableLiveData<SimpleState> by liveData()

    fun postLogin(username: String, password: String) {
        endpoint.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<SimpleState>(SimpleState::Result)
            .onErrorReturn(SimpleState::Error)
            .toFlowable()
            .startWith(SimpleState.Loading)
            .subscribe(stateLogin::postValue)
            .let { return@let CompositeDisposable::add }
    }
}