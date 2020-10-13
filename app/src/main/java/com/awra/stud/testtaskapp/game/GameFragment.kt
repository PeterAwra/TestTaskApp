package com.awra.stud.testtask.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.awra.stud.testtaskapp.R
import com.awra.stud.testtaskapp.databinding.GameLayoutBinding
import com.awra.stud.testtaskapp.game.Game
import com.awra.stud.testtaskapp.game.ViewDrawLine

class GameFragment : Fragment() {
    val game: Game = Game()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutBinding = DataBindingUtil.inflate<GameLayoutBinding>(
            inflater,
            R.layout.game_layout,
            container,
            false
        )
        layoutBinding.game = game
        val overlayLineView = ViewDrawLine(requireContext())
        layoutBinding.root.findViewById<LinearLayout>(R.id.grid_view).overlay.add(overlayLineView)
        game.callbackDrawLine = overlayLineView::drawLine
        game.restart()
        return layoutBinding.root
    }
}

@BindingAdapter("app:finishMsg")
fun setMsgWin(view: TextView, stringId: Int?) {
    view.visibility = if (stringId == null) View.INVISIBLE else View.VISIBLE
    stringId?.let { view.setText(it) }
}

