package com.websarva.wings.android.androidpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    // ViewModelに属する
    val userName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    init{
        userName.value = "そら"
    }
}