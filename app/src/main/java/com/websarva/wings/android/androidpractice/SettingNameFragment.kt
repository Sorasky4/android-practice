package com.websarva.wings.android.androidpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels

class SettingNameFragment : Fragment() {
    // ActivityのスコープでViewModelを定義し、ViewModelのインスタンスを作成
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btSaveName).apply {
            setOnClickListener{
                val userName = view.findViewById<EditText>(R.id.etMyName).text.toString()
                viewModel.saveName(userName)
            }
        }
    }
}