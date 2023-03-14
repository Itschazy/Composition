package com.chxzyfps.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chxzyfps.composition.R
import com.chxzyfps.composition.data.GameRepositoryImpl
import com.chxzyfps.composition.databinding.FragmentGameBinding
import com.chxzyfps.composition.domain.entity.GameResult
import com.chxzyfps.composition.domain.entity.GameSettings
import com.chxzyfps.composition.domain.entity.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var level: Level
    private lateinit var gameSettings: GameSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        gameSettings = GameRepositoryImpl.getGameSettings(level)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvSum.setOnClickListener {
                launchGameFinishedFragment()
            }
        }
    }

    private fun launchGameFinishedFragment() {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.main_container, GameFinishedFragment.newInstance(
                GameResult(
                    true, 5, 5, gameSettings
                )
            )
        ).addToBackStack(null).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun parseArgs() {
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    companion object {

        const val NAME = "GameFragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }
}
