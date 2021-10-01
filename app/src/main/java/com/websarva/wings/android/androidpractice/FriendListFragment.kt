package com.websarva.wings.android.androidpractice

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class FriendListFragment : Fragment() {
    // SimpleAdapterで使用するMutableListオブジェクトを用意
    private var _friendList: MutableList<MutableMap<String, Any>>? = null
    // SimpleAdapter第4引数用のデータ
    private val FROM = arrayOf("name", "location", "status", "icon")
    // SimpleAdapter第5引数用のデータ
    private val TO = intArrayOf(R.id.friendName, R.id.friendLocation, R.id.status, R.id.icon)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // フラグメントで表示する画面をxmlファイルからインフレートして返す
        return inflater.inflate(R.layout.fragment_friend_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 画面部品ListViewを取得
        val lvFriend = view.findViewById<ListView>(R.id.lvFriends)
        // SimpleAdapterで使用するMutableListオブジェクトにデータをいれる
        _friendList = createFriendList()
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(activity, _friendList, R.layout.inline_list_item, FROM, TO)
        // アダプターの登録
        lvFriend.adapter = adapter
        // リストタップのリスナ登録
        lvFriend.onItemClickListener = ListItemClickListener()
    }

    private fun createFriendList(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var friend: MutableMap<String, Any> = mutableMapOf("name" to "maki", "location" to "大講義室", "status" to getString(R.string.status_free), "icon" to R.drawable.maki_icon)
        friendList.add(friend)
        friend = mutableMapOf("name" to "あみみ", "location" to "エレ工", "status" to getString(R.string.status_busy))
        friendList.add(friend)
        friend = mutableMapOf("name" to "t4t5u0", "location" to "595教室", "status" to getString(R.string.status_offline))
        friendList.add(friend)
        return friendList
    }

    // リストアイテムがタップされたときの処理
    private fun showDetail(friend: MutableMap<String, Any>) {
        val name = friend["name"] as String
        val location = friend["location"] as String
        val status = friend["status"] as String
        var icon: Int? = null
        if (friend.containsKey("icon")) icon = friend["icon"] as Int

        Intent(activity, FriendDetailActivity::class.java).apply {
            putExtra("name", name)
            putExtra("location", location)
            putExtra("status", status)
            putExtra("icon", icon)
            startActivity(this)
        }
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val friend = _friendList!![position]
            showDetail(friend)
        }
    }
}