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
        // フラグメントで表示する画面をxmlファイルからインフレートする
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        // 画面部品ListViewを取得
        val lvFriend = view.findViewById<ListView>(R.id.lvFriends)
        // SimpleAdapterで使用するMutableListオブジェクトにデータをいれる
        _friendList = createFriendList()
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(activity, _friendList, R.layout.inline_list_item, FROM, TO)
        // アダプターの登録
        lvFriend.adapter = adapter
        // リストタップのリスナ登録
        // lvFriend.onItemClickListener = ListItemClickListener()
        // インフレートされた画面を戻り値として返す
        return view
    }

    private fun createFriendList(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var friend: MutableMap<String, Any> = mutableMapOf("name" to "そら", "location" to "講堂", "status" to getString(R.string.status_online), "icon" to R.drawable.sora_icon)
        friendList.add(friend)
        friend = mutableMapOf("name" to "maki", "location" to "大講義室", "status" to getString(R.string.status_free), "icon" to R.drawable.ic_baseline_self_improvement_24)
        friendList.add(friend)
        friend = mutableMapOf("name" to "あみみ", "location" to "エレ工", "status" to getString(R.string.status_busy))
        friendList.add(friend)
        friend = mutableMapOf("name" to "t4t5u0", "location" to "595教室", "status" to getString(R.string.status_offline))
        friendList.add(friend)
        return friendList
    }

    private fun createFriendListOnline(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = createFriendList()
        val onlineFriends: MutableList<MutableMap<String, Any>> = mutableListOf()
        for (item in friendList) {
            if(item["status"] == getString(R.string.status_online)) onlineFriends.add(item)
        }
        return onlineFriends
    }

    private fun createFriendListBusy(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = createFriendList()
        val busyFriends: MutableList<MutableMap<String, Any>> = mutableListOf()
        for (item in friendList) {
            if(item["status"] == getString(R.string.status_busy)) busyFriends.add(item)
        }
        return busyFriends
    }

    private fun createFriendListFree(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = createFriendList()
        val freeFriends: MutableList<MutableMap<String, Any>> = mutableListOf()
        for (item in friendList) {
            if(item["status"] == getString(R.string.status_free)) freeFriends.add(item)
        }
        return freeFriends
    }

    private fun createFriendListOffline(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = createFriendList()
        val offlineFriends: MutableList<MutableMap<String, Any>> = mutableListOf()
        for (item in friendList) {
            if(item["status"] == getString(R.string.status_offline)) offlineFriends.add(item)
        }
        return offlineFriends
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.friendListOptionAll ->
                _friendList = createFriendList()
            R.id.friendListOptionOnline ->
                _friendList = createFriendListOnline()
            R.id.friendListOptionBusy ->
                _friendList = createFriendListBusy()
            R.id.friendListOptionFree ->
                _friendList = createFriendListFree()
            R.id.friendListOptionOffline ->
                _friendList = createFriendListOffline()
        }
        val lvFriend = view?.findViewById<ListView>(R.id.lvFriends)
        val adapter = SimpleAdapter(activity, _friendList, R.layout.inline_list_item, FROM, TO)
        lvFriend?.adapter = adapter
        return super.onOptionsItemSelected(item)
    }

    // リストアイテムがタップされたときの処理
    private fun showDetail(friend: MutableMap<String, Any>) {
        val name = friend["name"] as String
        val location = friend["location"] as String
        val status = friend["status"] as String
        var icon: Int? = null
        if (friend.containsKey("icon")) icon = friend["icon"] as Int

        val intent = Intent(activity, FriendDetailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("location", location)
        intent.putExtra("status", status)
        intent.putExtra("icon", icon)
        startActivity(intent)
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val friend = _friendList!![position]
            showDetail(friend)
        }
    }
}