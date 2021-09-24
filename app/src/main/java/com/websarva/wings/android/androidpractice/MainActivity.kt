package com.websarva.wings.android.androidpractice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // WalkTroughActivity.showIfNeeded(this, savedInstanceState)
        WalkTroughActivity.showForcibly(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(R.string.toolbar_title)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setSubtitle(R.string.toolbar_subtitle1)
        toolbar.setSubtitleTextColor(Color.LTGRAY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lvFriend = findViewById<ListView>(R.id.lvFriend)
        val friendsList: MutableList<MutableMap<String, String>> = mutableListOf()
        var friend = mutableMapOf("name" to "そら", "location" to "講堂")
        friendsList.add(friend)
        friend = mutableMapOf("name" to "maki", "location" to "大講義室")
        friendsList.add(friend)
        friend = mutableMapOf("name" to "あみみ", "location" to "エレ工")
        friendsList.add(friend)
        friend = mutableMapOf("name" to "t4t5u0", "location" to "595教室")
        friendsList.add(friend)

        val from = arrayOf("name", "location")
        val to = intArrayOf(R.id.friendName, R.id.friendLocation)
        val adapter = SimpleAdapter(applicationContext, friendsList, R.layout.inline_list_item_2, from, to)
        lvFriend.adapter = adapter

        lvFriend.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // val item = (view as TextView).text.toString()
                val item = parent.getItemAtPosition(position) as String
                val show = "${item}を選択しました"
                Toast.makeText(applicationContext, show, Toast.LENGTH_LONG).show()
            }
        })
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
}