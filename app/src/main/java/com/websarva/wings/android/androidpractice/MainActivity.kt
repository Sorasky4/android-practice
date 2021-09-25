package com.websarva.wings.android.androidpractice

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
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
        // コンテキストメニューを表示するビューを登録
        registerForContextMenu(lvFriend)
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

    // contextメニューで詳細を表示がタップされたときの処理
    private fun showDetail(friend: MutableMap<String, Any>) {
        val name = friend["name"] as String
        val location = friend["location"] as String
        val status = friend["status"] as String
        var icon: Int? = null
        if (friend.containsKey("icon")) icon = friend["icon"] as Int

        val intent = Intent(applicationContext, FriendDetailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("location", location)
        intent.putExtra("status", status)
        intent.putExtra("icon", icon)
        startActivity(intent)
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

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        // 親クラスの同名メソッドの呼び出し
        super.onCreateContextMenu(menu, view, menuInfo)
        // コンテキストメニュー用xmlファイルをインフレート
        menuInflater.inflate(R.menu.menu_context_friend_list, menu)
        // コンテキストメニューのヘッダーファイルを設定
        menu.setHeaderTitle(R.string.friend_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val friend = _friendList!![listPosition]

        when(item.itemId) {
            R.id.friendListContextStay -> {
                val stayTime = "57分"
                Toast.makeText(applicationContext, stayTime, Toast.LENGTH_LONG).show()
            }
            R.id.friendListContextDetail ->
                showDetail(friend)
        }
        return super.onContextItemSelected(item)
    }
}