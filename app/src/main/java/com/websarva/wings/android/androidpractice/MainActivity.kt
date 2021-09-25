package com.websarva.wings.android.androidpractice

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var _friendList: MutableList<MutableMap<String, Any>>? = null
    private val FROM = arrayOf("name", "location", "status", "icon")
    private val TO = intArrayOf(R.id.friendName, R.id.friendLocation, R.id.status, R.id.icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WalkTroughActivity.showIfNeeded(this, savedInstanceState)
        // WalkTroughActivity.showForcibly(this)

        _friendList = createFriendList()
        val lvFriend = findViewById<ListView>(R.id.lvFriend)
        val adapter = SimpleAdapter(applicationContext, _friendList, R.layout.inline_list_item_2, FROM, TO)
        lvFriend.adapter = adapter
        // リストタップのリスナクラス登録
        lvFriend.onItemClickListener = ListItemClickListener()
    }

    private fun createFriendList(): MutableList<MutableMap<String, Any>> {
        val friendList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var friend: MutableMap<String, Any> = mutableMapOf("name" to "そら", "location" to "講堂", "status" to getString(R.string.status_online), "icon" to R.drawable.sora_icon)
        friendList.add(friend)
        friend = mutableMapOf("name" to "maki", "location" to "大講義室", "status" to getString(R.string.status_free))
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

    // リストがタップされたときの処理が記述されたメンバクラス
    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val name = item["name"]
            val location = item["location"]
            // ダイアログフラグメントオブジェクトの生成
            val dialogFragment = DisplayConfirmDialogFragment()
            val args = Bundle()
            args.putString("name", name)
            args.putString("location", location)
            dialogFragment.arguments = args
            dialogFragment.show(supportFragmentManager, "DisplayConfirmDialogFragment")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // オプションメニュー用xmlファイルをインフレート
        menuInflater.inflate(R.menu.menu_options_friend_list, menu)
        // 親クラスの同名メソッドを呼び出し、その戻り値をリターン
        return super.onCreateOptionsMenu(menu)
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
        val lvFriend = findViewById<ListView>(R.id.lvFriend)
        val adapter = SimpleAdapter(applicationContext, _friendList, R.layout.inline_list_item_2, FROM, TO)
        lvFriend.adapter = adapter
        return super.onOptionsItemSelected(item)
    }
}