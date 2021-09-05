package com.abln.game2048

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gamedashboard.*

class GameDashboardActivity : AppCompatActivity() {


    val colorMapper: HashMap<Int, String> = HashMap();

    enum class SwipeDirection {
        top,
        bottom,
        left,
        right
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamedashboard)

        colorMapper[2] = "#121212"
        colorMapper[4] = "#131313"
        colorMapper[8] = "#141414"
        colorMapper[16] = "#151515"
        colorMapper[32] = "#161616"
        colorMapper[64] = "#171717"
        colorMapper[128] = "#181818"
        colorMapper[256] = "#191919"
        colorMapper[512] = "#202020"
        colorMapper[1024] = "#212121"
        colorMapper[2048] = "#222222"

        colorMapper[2048]?.let { Log.d("PrintMsg", it) }
        print(colorMapper[2048])

        addSwipeGesture(swipeArea)

    }

    fun addSwipeGesture(view: View) {
        view.isClickable = true

        view.setOnTouchListener(object : OnSwipeTouchListener(this@GameDashboardActivity) {

            override fun onSwipeTop() {
                super.onSwipeTop()
                Log.d("GameDashboard","top"+SwipeDirection.top);
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
                Log.d("GameDashboard","bottom"+SwipeDirection.bottom);
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Log.d("GameDashboard","left"+SwipeDirection.left);
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                Log.d("GameDashboard","right"+SwipeDirection.right);

            }
        })
    }

    fun removeSwipeGesture(view: View) {
        view.isClickable = false
    }
}