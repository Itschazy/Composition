package com.chxzyfps.composition.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.chxzyfps.composition.R
import com.chxzyfps.composition.domain.entity.GameResult
import com.chxzyfps.composition.domain.entity.Question
import java.security.cert.PKIXRevocationChecker.Option

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score), count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers), count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage), count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    val count = getPercentageOfRightAnswers(gameResult)
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage), count
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, gameResult: GameResult) {
    imageView.setImageResource(getEmojiResource(gameResult))
}

@BindingAdapter("tvSum")
fun bindTvSum(textView: TextView, question: Question) {
    textView.text = question.sum.toString()
}

@BindingAdapter("tvLeftNumber")
fun bindTvLeftNumber(textView: TextView, question: Question) {
    textView.text = question.visibleNumber.toString()
}

@BindingAdapter("tvAnswersProgress")
fun bindTvAnswersProgress(textView: TextView, progressAnswers: String) {
    textView.text = progressAnswers
}

@BindingAdapter("progressBarProgress")
fun bindProgressBar(progressBar: ProgressBar,  number: Int) {
    progressBar.setProgress(number, true)
}

@BindingAdapter("progressBarColor")
fun bindProgressBarColor(progressBar: ProgressBar, enoughPercent: Boolean) {
    val colorResId = if (enoughPercent) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(progressBar.context, colorResId)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("answersProgressColor")
fun bindAnswersProgressColor(textView: TextView, enoughCount: Boolean) {
    val colorResId = if (enoughCount) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(textView.context, colorResId)
    textView.setTextColor(ColorStateList.valueOf(color))
}

@BindingAdapter("progressBarSecondaryProgressTint")
fun bindProgressBarSecondaryProgressTint(progressBar: ProgressBar, minPercent: Int) {
    progressBar.secondaryProgress = minPercent
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, onOptionClickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        onOptionClickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getPercentageOfRightAnswers(gameResult: GameResult) = with (gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100 ).toInt()
    }
}

private fun getEmojiResource(gameResult: GameResult): Int {
    return if (gameResult.winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}