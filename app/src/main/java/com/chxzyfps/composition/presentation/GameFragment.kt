package com.chxzyfps.composition.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.chxzyfps.composition.R
import com.chxzyfps.composition.data.GameRepositoryImpl
import com.chxzyfps.composition.databinding.FragmentGameBinding
import com.chxzyfps.composition.domain.entity.GameResult
import com.chxzyfps.composition.domain.entity.GameSettings
import com.chxzyfps.composition.domain.entity.Level

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(
            this, viewModelFactory
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        setClickListenersOnOptions()
    }

    private fun setClickListenersOnOptions() {
        binding.apply {
            for (tvOption in tvOptions) {
                tvOption.setOnClickListener {
                    viewModel?.chooseAnswer(tvOption.text.toString().toInt())
                }
            }
        }
    }

    private fun observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.apply {
//                for (i in 0 until tvOptions.size) {
//                    tvOptions[i].text = it.options[i].toString()
//                }
//            }
//        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
