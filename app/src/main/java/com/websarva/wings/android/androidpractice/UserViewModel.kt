package com.websarva.wings.android.androidpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// アクティビティを破棄してもデータを保存・復元する場合、ViewModelの第一引数にSavedStateHandleを指定
class UserViewModel(private val state: SavedStateHandle): ViewModel() {
    // SavedStateHandleからキー"USER_NAME"にひも付くMutableLiveDataをもってくる
    // 無い場合は初期値を空文字にして新しく作り、SavedStateHandle内でキー"USER_NAME"に紐つける
    val userName: MutableLiveData<String> by lazy {
        state.getLiveData<String>("USER_NAME", "")
    }
    fun saveName(userName: String) {
        state.set("USER_NAME", userName)
    }
}