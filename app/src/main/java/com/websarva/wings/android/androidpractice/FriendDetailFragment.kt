package com.websarva.wings.android.androidpractice

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FriendDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // フラグメントで表示する画面をxmlファイルからインフレートし、返す
        return inflater.inflate(R.layout.fragment_friend_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 所属アクティビティからインテントを取得
        val intent = activity?.intent
        // インテントから引き継ぎデータをまとめたもの(Bundleオブジェクト)を取得
        val extras = intent?.extras
        // 名前, 居場所, ステータス, アイコンを表示させるTextView, ImageViewを取得し
        // TextView, ImageViewに名前, 居場所, ステータス, アイコンを表示
        view.apply {
            findViewById<TextView>(R.id.tvFriendName).apply {
                text = extras?.getString("name")
            }
            findViewById<TextView>(R.id.tvFriendLocation).apply {
                text = extras?.getString("location")
            }
            findViewById<TextView>(R.id.tvFriendStatus).apply {
                text = extras?.getString("status")
            }
            findViewById<ImageView>(R.id.ivFriendIcon).apply {
                setImageResource(extras?.getInt("icon", R.drawable.ic_baseline_mood_24)!!)
            }
        }
        // actionbarとバックボタンを有効にする
        setHasOptionsMenu(true)
        setupBackButton()
    }

    // ボタンが押されたときの処理が記述されたメンバクラス
    private inner class ButtonClickListener: View.OnClickListener{
        override fun onClick(view: View) {
            activity?.finish()
        }
    }

    // アクションバーのバックボタンを表示するメソッド
    private fun setupBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    // actionbarのバックボタンが押されたときの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}