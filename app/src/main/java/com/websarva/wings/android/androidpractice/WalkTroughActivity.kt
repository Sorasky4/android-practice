package com.websarva.wings.android.androidpractice

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stephentuso.welcome.*

class WalkTroughActivity : WelcomeActivity() {
    companion object {
        // 最初の一度だけ呼び出す
        fun showIfNeeded(activity: Activity, savedInstanceState: Bundle?) {
            WelcomeHelper(activity, WalkTroughActivity::class.java).show(savedInstanceState)
        }
        // 強制的に呼び出す
        fun showForcibly(activity: Activity) {
            WelcomeHelper(activity, WalkTroughActivity::class.java).forceShow()
        }
    }

    override fun configuration(): WelcomeConfiguration {
        return WelcomeConfiguration.Builder(this)
            .defaultBackgroundColor(BackgroundColor(Color.LTGRAY))
            .page(TitlePage(R.mipmap.ic_launcher, "Title"))
            .page(BasicPage(
                R.drawable.ic_add_person_foreground,
                "友人を登録する",
                "IDを入力して友人を登録しましょう"
                ).background(R.color.green))
            .page(BasicPage(
                R.drawable.ic_baseline_person_pin_circle_24,
                "友人の居場所を知る",
                "友人を一覧表示して、今どこにいるのかを知ることができます"
                ).background(R.color.green))
            .page(BasicPage(
                R.drawable.ic_baseline_settings_24,
                "表示ステータスの設定",
                "画面右下のアイコンをタップして、「取り込み中」などのステータスを設定できます"
                ).background(R.color.green))
            .swipeToDismiss(true)
            .build()
    }
}