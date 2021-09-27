package com.websarva.wings.android.androidpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter

class SettingsFragment : Fragment() {
    // SimpleAdapterで使用するMutableListオブジェクトを用意
    private var _settingList: MutableList<MutableMap<String, Any>>? = null
    // SimpleAdapter第4引数用のデータ
    private val FROM = arrayOf("setting", "myStatus", "myIcon", "myName")
    // SimpleAdapter第5引数用のデータ
    private val TO = intArrayOf(R.id.tvSettings, R.id.tvMyStatus, R.id.ivMyIcon, R.id.tvMyName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 画面部品ListViewを取得
        val lvSettings = view.findViewById<ListView>(R.id.lvSettings)
        // SimpleAdapterで使用するMutableListオブジェクトにデータをいれる
        _settingList = createFriendList()
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(activity, _settingList, R.layout.settings_list_item, FROM, TO)
        // アダプターの登録
        lvSettings.adapter = adapter
        // リストタップのリスナ登録
        //lvSettings.onItemClickListener = ListItemClickListener()
    }

    private fun createFriendList(): MutableList<MutableMap<String, Any>> {
        val settingList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var setting: MutableMap<String, Any> = mutableMapOf("setting" to getString(R.string.settings_status), "myStatus" to getString(R.string.status_online))
        settingList.add(setting)
        setting = mutableMapOf("setting" to getString(R.string.settings_icon), "myIcon" to R.drawable.sora_icon)
        settingList.add(setting)
        setting = mutableMapOf("setting" to getString(R.string.settings_name), "myName" to "sora")
        settingList.add(setting)
        return settingList
    }
}