package com.abln.game2048

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gamedashboard.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GameDashboardActivity : AppCompatActivity() {

    val colorMapper: HashMap<Int, String> = HashMap();
    var filledCell = ArrayList<Int>()
    var currentState = Array(4) {Array(4) {0} }
    var updatedState = Array(4) {Array(4) {0} }

    enum class SwipeDirection {
        Top,
        Bottom,
        Left,
        Right
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamedashboard)

        colorMapper[0] = "#CDC1B4"
        colorMapper[2] = "#eee4da"
        colorMapper[4] = "#ede0c7"
        colorMapper[8] = "#f2b178"
        colorMapper[16] = "#f59563"
        colorMapper[32] = "#f67c5f"
        colorMapper[64] = "#f65d3b"
        colorMapper[128] = "#E4CF8B"
        colorMapper[256] = "#edcc61"
        colorMapper[512] = "#EEC43A"
        colorMapper[1024] = "#97ED61"
        colorMapper[2048] = "#B02DF2"

        addSwipeGesture(swipeArea)
        playFirstMove()
    }

    fun addSwipeGesture(view: View) {
        view.isClickable = true

        view.setOnTouchListener(object : OnSwipeTouchListener(this@GameDashboardActivity) {

            override fun onSwipeTop() {
                super.onSwipeTop()
                //call swipe default function
                swipeAction(SwipeDirection.Top)
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
                swipeAction(SwipeDirection.Bottom)
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                swipeAction(SwipeDirection.Left)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                swipeAction(SwipeDirection.Right)
            }
        })
    }

    fun swipeAction(action: SwipeDirection) {
        //save the current state into two dimensional array
        saveCurrentStateTo2DArray()

        when(action) {
            SwipeDirection.Top -> {
                performActionTop()
            }

            SwipeDirection.Bottom -> {
                performActionBottom()
            }

            SwipeDirection.Left -> {
                performActionLeft()
            }

            SwipeDirection.Right -> {
                performActionRight()
            }
        }

        loadFromUpdatedStateToUI()
        updateUIaccordingToNumber()
        //Call value in empty space
        generateNewRandomNumberAfterSwipeAction()
    }

    fun updateUIaccordingToNumber() {
        //First row
        tv1.setBackgroundColor(Color.parseColor(colorMapper[updatedState[0][0]]))
        tv2.setBackgroundColor(Color.parseColor(colorMapper[updatedState[0][1]]))
        tv3.setBackgroundColor(Color.parseColor(colorMapper[updatedState[0][2]]))
        tv4.setBackgroundColor(Color.parseColor(colorMapper[updatedState[0][3]]))

        //Second row
        tv5.setBackgroundColor(Color.parseColor(colorMapper[updatedState[1][0]]))
        tv6.setBackgroundColor(Color.parseColor(colorMapper[updatedState[1][1]]))
        tv7.setBackgroundColor(Color.parseColor(colorMapper[updatedState[1][2]]))
        tv8.setBackgroundColor(Color.parseColor(colorMapper[updatedState[1][3]]))

        //Third row
        tv9.setBackgroundColor(Color.parseColor(colorMapper[updatedState[2][0]]))
        tv10.setBackgroundColor(Color.parseColor(colorMapper[updatedState[2][1]]))
        tv11.setBackgroundColor(Color.parseColor(colorMapper[updatedState[2][2]]))
        tv12.setBackgroundColor(Color.parseColor(colorMapper[updatedState[2][3]]))

        //Fourth row
        tv13.setBackgroundColor(Color.parseColor(colorMapper[updatedState[3][0]]))
        tv14.setBackgroundColor(Color.parseColor(colorMapper[updatedState[3][1]]))
        tv15.setBackgroundColor(Color.parseColor(colorMapper[updatedState[3][2]]))
        tv16.setBackgroundColor(Color.parseColor(colorMapper[updatedState[3][3]]))
    }

    fun performActionTop() {
//        copy current state to final state
        updatedState = currentState

        //check if first two rows can be added

        for(row:Int in 1 until currentState.size) {
            for(col: Int in currentState[row].indices) {
                if(currentState[row-1][col] == currentState[row][col]) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row-1][col] += currentState[row][col]
                    currentState[row][col] = 0
                }
            }
        }

        //move the position from bottom to top
        for(row in 3 downTo 1) {
            for(col: Int in currentState[row].indices) {

                if(currentState[row-1][col] == 0) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row-1][col] = currentState[row][col]
                    currentState[row][col] = 0
                }
            }
        }

        updateFreeCells()
        updatedState = currentState
    }

    fun performActionBottom() {
        //        copy current state to final state
        updatedState = currentState

        //check if first two rows can be added

        for(row:Int in currentState.size-1 downTo 1) {
            for(col: Int in currentState[row].indices) {
                if(currentState[row-1][col] == currentState[row][col]) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col] += currentState[row-1][col]
                    currentState[row-1][col] = 0
                }
            }
        }

        //move the position from bottom to top
        for(row in 1 until currentState.size) {
            for(col: Int in currentState[row].indices) {

                if(currentState[row][col] == 0) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col] = currentState[row-1][col]
                    currentState[row-1][col] = 0
                }
            }
        }

        updateFreeCells()
        updatedState = currentState

    }

    fun performActionLeft() {
        //        copy current state to final state
        updatedState = currentState

        //check if first two rows can be added

        for(col:Int in 1 until currentState.size) {
            for(row: Int in currentState[col].indices) {
                if(currentState[row][col-1] == currentState[row][col]) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col-1] += currentState[row][col]
                    currentState[row][col] = 0
                }
            }
        }

        //move the position from bottom to top
        for(col in 3 downTo 1) {
            for(row: Int in currentState[col].indices) {

                if(currentState[row][col-1] == 0) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col-1] = currentState[row][col]
                    currentState[row][col] = 0
                }
            }
        }

        updateFreeCells()
        updatedState = currentState
    }

    fun performActionRight() {
        //        copy current state to final state
        updatedState = currentState

        //check if first two rows can be added

        for(col:Int in currentState.size-1 downTo 1) {
            for(row: Int in currentState[col].indices) {
                if(currentState[row][col-1] == currentState[row][col]) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col] += currentState[row][col-1]
                    currentState[row][col-1] = 0
                }
            }
        }

        //move the position from bottom to top
        for(col in 1 until currentState.size) {
            for(row: Int in currentState[col].indices) {

                if(currentState[row][col] == 0) {
                    //add these tow number together and replace the currentState to 0
                    currentState[row][col] = currentState[row][col-1]
                    currentState[row][col-1] = 0
                }
            }
        }

        updateFreeCells()
        updatedState = currentState

    }

    fun updateFreeCells() {
        filledCell.clear()

        for(row: Int in 0 until 4) {
            if(currentState[0][row] != 0) {
                filledCell.add(row + 1)
            }

            if(currentState[1][row] != 0) {
                filledCell.add(row + 5)
            }

            if(currentState[2][row] != 0) {
                filledCell.add(row + 9)
            }

            if(currentState[3][row] != 0) {
                filledCell.add(row + 13)
            }
        }
        Log.d("TAG", "filledCellCount"+filledCell.size)
    }

    fun saveCurrentStateTo2DArray() {
        //FirstRow
        currentState[0][0] = tv1.text.toString().toIntWithNullHandle();
        currentState[0][1] = tv2.text.toString().toIntWithNullHandle();
        currentState[0][2] = tv3.text.toString().toIntWithNullHandle();
        currentState[0][3] = tv4.text.toString().toIntWithNullHandle();

        //SecondRow
        currentState[1][0] = tv5.text.toString().toIntWithNullHandle();
        currentState[1][1] = tv6.text.toString().toIntWithNullHandle();
        currentState[1][2] = tv7.text.toString().toIntWithNullHandle();
        currentState[1][3] = tv8.text.toString().toIntWithNullHandle();

        //ThirdRow
        currentState[2][0] = tv9.text.toString().toIntWithNullHandle();
        currentState[2][1] = tv10.text.toString().toIntWithNullHandle();
        currentState[2][2] = tv11.text.toString().toIntWithNullHandle();
        currentState[2][3] = tv12.text.toString().toIntWithNullHandle();

        //FourthRow
        currentState[3][0] = tv13.text.toString().toIntWithNullHandle();
        currentState[3][1] = tv14.text.toString().toIntWithNullHandle();
        currentState[3][2] = tv15.text.toString().toIntWithNullHandle();
        currentState[3][3] = tv16.text.toString().toIntWithNullHandle();
    }

    fun loadFromUpdatedStateToUI() {
        //FirstRow
        tv1.text = updatedState[0][0].toStringWithZeroHandle()
        tv2.text = updatedState[0][1].toStringWithZeroHandle()
        tv3.text = updatedState[0][2].toStringWithZeroHandle()
        tv4.text = updatedState[0][3].toStringWithZeroHandle()

        //SecondRow
        tv5.text = updatedState[1][0].toStringWithZeroHandle()
        tv6.text = updatedState[1][1].toStringWithZeroHandle()
        tv7.text = updatedState[1][2].toStringWithZeroHandle()
        tv8.text = updatedState[1][3].toStringWithZeroHandle()

        //ThirdRow
        tv9.text = updatedState[2][0].toStringWithZeroHandle()
        tv10.text = updatedState[2][1].toStringWithZeroHandle()
        tv11.text = updatedState[2][2].toStringWithZeroHandle()
        tv12.text = updatedState[2][3].toStringWithZeroHandle()

        //FourthRow
        tv13.text = updatedState[3][0].toStringWithZeroHandle()
        tv14.text = updatedState[3][1].toStringWithZeroHandle()
        tv15.text = updatedState[3][2].toStringWithZeroHandle()
        tv16.text = updatedState[3][3].toStringWithZeroHandle()
    }

    fun generateNewRandomNumberAfterSwipeAction() {
        var emptyCell = ArrayList<Int>()

        for (cellId in 1..16) {
            if((!filledCell.contains(cellId))) {
                emptyCell.add(cellId)
            }
        }

        if(emptyCell.size == 0) {
            Toast.makeText(this, "Game over! Start new game", Toast.LENGTH_SHORT).show()
            return;
        }
        Log.d("TAG","Emptycellsize"+emptyCell.size)
        val r = Random()
        val randIndex = r.nextInt(emptyCell.size)
        val cellId = emptyCell[randIndex]

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

fun String.toIntWithNullHandle(): Int {
    if(this.contentEquals("")) {
        return 0
    }
    return this.toInt()
}

fun Int.toStringWithZeroHandle(): String {
    if(this == 0) {
        return ""
    }
    return this.toString()
}