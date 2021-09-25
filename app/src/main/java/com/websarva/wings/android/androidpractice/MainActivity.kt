package com.websarva.wings.android.androidpractice

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var _friendList: MutableList<MutableMap<String, Any>>? = null
    val FROM = arrayOf("name", "location", "status", "icon")
    val TO = intArrayOf(R.id.friendName, R.id.friendLocation, R.id.status, R.id.icon)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WalkTroughActivity.showIfNeeded(this, savedInstanceState)
        // WalkTroughActivity.showForcibly(this)

        fun createFriendList(): MutableList<MutableMap<String, Any>> {
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

        _friendList = createFriendList()
        val lvFriend = findViewById<ListView>(R.id.lvFriend)
        val adapter = SimpleAdapter(applicationContext, _friendList, R.layout.inline_list_item_2, FROM, TO)
        lvFriend.adapter = adapter
        // リストタップのリスナクラス登録
        lvFriend.onItemClickListener = ListItemClickListener()
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
}