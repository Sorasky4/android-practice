package com.websarva.wings.android.androidpractice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DisplayConfirmDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // ダイアログビルダーを生成
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_msg)
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())
        builder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())
        // ダイアログオブジェクトを生成し、リターン
        return builder.create()
    }

    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface, which: Int) {
            // トーストメッセージ用の文字列変数
            var msg = ""
            // タップされたアクションボタンで分岐
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    msg = getString(R.string.dialog_ok_toast)
                    val intent = Intent(context, FriendDetailActivity::class.java)
                    val bundle = arguments
                    if (bundle != null) {
                        intent.putExtra("name", bundle.getString("name"))
                        intent.putExtra("location", bundle.getString("location"))
                    }
                    startActivity(intent)
                }
                DialogInterface.BUTTON_NEGATIVE ->
                    msg = getString(R.string.dialog_ng_toast)
            }
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }
    }
}