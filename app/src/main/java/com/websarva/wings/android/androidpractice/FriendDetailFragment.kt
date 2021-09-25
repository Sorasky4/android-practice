package com.websarva.wings.android.androidpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class FriendDatailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // フラグメントで表示する画面をxmlファイルからインフレートする
        val view = inflater.inflate(R.layout.fragment_friend_detail, container, false)
        // 所属アクティビティからインテントを取得
        val intent = activity?.intent
        // インテントから引き継ぎデータをまとめたもの(Bundleオブジェクト)を取得
        val extras = intent?.extras
        // 名前, 居場所, ステータスを取得
        val name = extras?.getString("name")
        val location = extras?.getString("location")
        val status = extras?.getString("status")
        // アイコンを取得
        val icon = extras?.getInt("icon", R.drawable.ic_baseline_mood_24)
        // 名前, 居場所, ステータスを表示させるTextView取得
        val tvFriendName = view.findViewById<TextView>(R.id.tvFriendName)
        val tvFriendLocation = view.findViewById<TextView>(R.id.tvFriendLocation)
        val tvFriendStatus = view.findViewById<TextView>(R.id.tvFriendStatus)
        // アイコンを表示させるImageViewを取得
        val ivFriendIcon = view.findViewById<ImageView>(R.id.ivFriendIcon)
        // TextViewに名前, 居場所, ステータスを表示
        tvFriendName.text = name
        tvFriendLocation.text = location
        tvFriendStatus.text = status
        // ImageViewにアイコンを表示
        ivFriendIcon.setImageResource(icon!!)
        // 戻るボタンを取得
        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        // 戻るボタンにリスナを登録
        btBackButton.setOnClickListener(ButtonClickListener())
        // インフレートされた画面を戻り値として返す
        return view
    }

    // ボタンが押されたときの処理が記述されたメンバクラス
    private inner class ButtonClickListener: View.OnClickListener{
        override fun onClick(view: View) {
            activity?.finish()
        }
    }
}