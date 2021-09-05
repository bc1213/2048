package com.abln.game2048

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_gamedashboard.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GameDashboardActivity : AppCompatActivity() {


    val colorMapper: HashMap<Int, String> = HashMap();
    var filledCell = ArrayList<Int>()

    enum class SwipeDirection {
        top,
        bottom,
        left,
        right
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamedashboard)

        colorMapper[2] = "#eee4da"// R.color.cellLayoutBgColor_2.toString()
        colorMapper[4] = "#ede0c7"//R.color.cellLayoutBgColor_4.toString()
        colorMapper[8] = "#f2b178"//R.color.cellLayoutBgColor_8.toString()
        colorMapper[16] = "#f59563"//R.color.cellLayoutBgColor_16.toString()
        colorMapper[32] = "#f67c5f"// R.color.cellLayoutBgColor_32.toString()
        colorMapper[64] = "#f65d3b"// R.color.cellLayoutBgColor_64.toString()
        colorMapper[128] = "#E4CF8B"//R.color.cellLayoutBgColor_128.toString()
        colorMapper[256] = "#edcc61"//R.color.cellLayoutBgColor_256.toString()
        colorMapper[512] = "#EEC43A"//R.color.cellLayoutBgColor_512.toString()
        colorMapper[1024] = "#97ED61"//R.color.cellLayoutBgColor_1024.toString()
        colorMapper[2048] = "#B02DF2"//R.color.cellLayoutBgColor_2048.toString()

        colorMapper[2048]?.let { Log.d("PrintMsg", it) }
        print(colorMapper[2048])

        addSwipeGesture(swipeArea)
        playFirstMove()
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

    fun launchNewGame() {
        //reset all cells
        restartGame()

        // use random generator and place 2's and 4's inside the 16 places

    }

    fun restartGame() {

        filledCell.clear()

        for (cellId in 1..16) {
            var tvSelected: TextView?
            tvSelected = when(cellId) {
                1-> tv1
                2-> tv2
                3-> tv3
                4-> tv4
                5-> tv5
                6-> tv6
                7-> tv7
                8-> tv8
                9-> tv9
                10-> tv10
                11-> tv11
                12-> tv12
                13-> tv13
                14-> tv14
                15-> tv15
                16-> tv16
                else -> { tv1}
            }
            tvSelected?.text = ""
            tvSelected?.setBackgroundResource(R.color.cellDefaultColor)
        }

        //play first action : place 2' in two places randomly
        playFirstMove()
    }

    /*
    * In the 2048 game, 2's appear 90% of the time; 4's appear 10% of the time
    * */
    fun generateCellRandomNumber(): Int {
        return if (Math.random() < 0.9) 2 else 4
    }

    fun playFirstMove() {
        var emptyCell = ArrayList<Int>()

        for (cellId in 1..16) {
            if((!filledCell.contains(cellId))) {
                emptyCell.add(cellId)
            }
        }
        val ints = Random().ints(1, emptyCell.size).distinct().limit(2).toArray()

        val cellId = emptyCell[ints[0]]

        var tvSelected: TextView?
        tvSelected = when(cellId) {
            1-> tv1
            2-> tv2
            3-> tv3
            4-> tv4
            5-> tv5
            6-> tv6
            7-> tv7
            8-> tv8
            9-> tv9
            10-> tv10
            11-> tv11
            12-> tv12
            13-> tv13
            14-> tv14
            15-> tv15
            16-> tv16
            else -> { tv1}
        }

        playGame(cellId, tvSelected, generateCellRandomNumber())

        val cellId2 = emptyCell[ints[1]]

        var tvSelected1: TextView?
        tvSelected1 = when(cellId2) {
            1-> tv1
            2-> tv2
            3-> tv3
            4-> tv4
            5-> tv5
            6-> tv6
            7-> tv7
            8-> tv8
            9-> tv9
            10-> tv10
            11-> tv11
            12-> tv12
            13-> tv13
            14-> tv14
            15-> tv15
            16-> tv16
            else -> { tv1}
        }

        playGame(cellId2, tvSelected1, generateCellRandomNumber())
    }

    fun playGame(cellId: Int, tvSelected: TextView, value: Int) {
        filledCell.add(cellId)
        tvSelected.text = value.toString()
        tvSelected.setBackgroundColor(Color.parseColor(colorMapper[value]))
    }

    fun launchNewGame(view: android.view.View) {
        restartGame()
    }
}