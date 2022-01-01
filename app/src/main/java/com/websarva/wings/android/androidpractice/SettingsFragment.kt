package com.websarva.wings.android.androidpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class SettingsFragment : Fragment() {
    // SimpleAdapterで使用するMutableListオブジェクトを用意
    private var _settingList: MutableList<MutableMap<String, Any>>? = null
    // SimpleAdapter第4引数用のデータ
    private val FROM = arrayOf("setting", "myStatus", "myIcon", "myName")
    // SimpleAdapter第5引数用のデータ
    private val TO = intArrayOf(R.id.tvSettings, R.id.tvMyStatus, R.id.ivMyIcon, R.id.tvMyName)
    // ActivityのスコープでViewModelを定義し、ViewModelのインスタンスを作成
    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewModelから取得したデータを表示
        showUserInfo()
        // 画面部品ListViewを取得
        val lvSettings = view.findViewById<ListView>(R.id.lvSettings)
        // SimpleAdapterで使用するMutableListオブジェクトにデータをいれる
        _settingList = createSettingList()
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(activity, _settingList, R.layout.settings_list_item, FROM, TO)
        // アダプターの登録
        lvSettings.adapter = adapter
        // リストタップのリスナ登録
        lvSettings.onItemClickListener = ListItemClickListener()
    }

    private fun createSettingList(): MutableList<MutableMap<String, Any>> {
        val settingList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var setting: MutableMap<String, Any> = mutableMapOf("setting" to getString(R.string.settings_status), "myStatus" to getString(R.string.status_online))
        settingList.add(setting)
        setting = mutableMapOf("setting" to getString(R.string.settings_icon), "myIcon" to R.drawable.sora_icon)
        settingList.add(setting)
        setting = mutableMapOf("setting" to getString(R.string.settings_name), "myName" to viewModel.userName.value!!)
        settingList.add(setting)
        return settingList
    }

    private fun showUserInfo(){
        view?.apply {
            findViewById<TextView>(R.id.tvMyName).apply {
                text = viewModel.userName.value!!
            }
        }
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            (parent.getItemAtPosition(position) as MutableMap<String, Any>).apply {
                when(this["setting"]) {
                    getString(R.string.settings_status) -> {
                        val bottomSheet = ModalBottomSheetFragment()
                        bottomSheet.show(childFragmentManager, ModalBottomSheetFragment.TAG)
                    }
                    getString(R.string.settings_name) -> {
                        findNavController().navigate(R.id.action_settingsFragment_to_settingNameFragment)
                    }
                    getString(R.string.settings_icon) -> {
                        //TODO:: write a process when tap "setting icon"
                    }
                }
            }
        }
    }
}