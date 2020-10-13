package com.awra.stud.testtaskapp.game

import androidx.databinding.ObservableField
import com.awra.stud.testtaskapp.R
import com.awra.stud.testtaskapp.log
import kotlin.random.Random

class Game {
    var callbackDrawLine: (fromCell: Int, toCell: Int) -> Unit = { fromCell, toCell -> }
    val finishGameObservable = ObservableField<Int>()
    var userMoveObservable = ObservableField<Boolean>(true)
    private var userSymbol = "X"
    private var opponentSymbol = "O"
    private var emptySymbol = ""
    private var indexes = List(9) { it }.shuffled().toMutableList()
    private var userIndexes = MutableList(0) { 0 }
    private var opponentIndexes = MutableList(0) { 0 }
    private var gameFinish = true
    val arrayValues = Array<ObservableField<String>>(9) { ObservableField(emptySymbol) }
    private var userMove = false
        set(value) {
            field = value
            userMoveObservable.set(field)
            if (!field) moveOpponent()
        }

    fun clickUser(button: Int) {
        "click $button".log()
        if (!gameFinish && indexes.contains(button)) {
            indexes.remove(button)
            userIndexes.add(button)
            arrayValues[button].set(userSymbol)
            checkFinish()
            userMove = false
        }
        Int
    }

    fun restart() {
        "click restart".log()
        arrayValues.forEach { it.set(emptySymbol) }
        indexes = List(9) { it }.shuffled().toMutableList()
        gameFinish = false
        userIndexes.clear()
        opponentIndexes.clear()
        finishGameObservable.set(null)
        callbackDrawLine(0, 0)
        userMove = Random.nextBoolean()
    }

    private fun moveOpponent() {
        if (!gameFinish) {
            arrayValues[indexes[0]].set(opponentSymbol)
            opponentIndexes.add(indexes[0])
            indexes.removeAt(0)
            checkFinish()
            userMove = true
        }
    }

    private fun checkEmpty(index: Int) = arrayValues[index].get().equals(emptySymbol)


    private fun checkFinish() {
        if (indexes.isEmpty()) {
            finishGameObservable.set(R.string.msg_tie)
            gameFinish = true
        }
        val tempIndexes = if (userMove) userIndexes else opponentIndexes
        finishCombinations
            .find { tempIndexes.containsAll(it) }
            ?.let {
                finishGameObservable.set(if (userMove) R.string.msg_you_won else R.string.msg_you_lose)
                gameFinish = true
                callbackDrawLine(it.first(), it.last())
            }
    }

    val finishCombinations = arrayOf(
        setOf(0, 1, 2),
        setOf(3, 4, 5),
        setOf(6, 7, 8),

        setOf(0, 3, 6),
        setOf(1, 4, 7),
        setOf(2, 5, 8),

        setOf(0, 4, 8),
        setOf(2, 4, 6),
    )
}