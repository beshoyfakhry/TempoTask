package com.tempo.task.utils

import android.annotation.SuppressLint
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tempo.task.R
import java.time.Duration
import java.time.Instant

object Extensions {


    fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE)
                    as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }


    fun ImageView.loadImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.broken_image)
            .into(this)
    }

    @SuppressLint("DefaultLocale")
    fun String.dateToAgo(now:Instant): String {

        val then: Instant = Instant.parse(this)
        val now: Instant = now
        val duration: Duration = Duration.between(then, now)

        var concatString = ""
        if (duration.toDays().toInt() != 0) {
            concatString = "%02d days ago"

            if (duration.toDays().toInt() == 1)
                concatString = "%02d day ago"


            return Utils.formatStringToAgo(
                concatString, duration,
                duration.toDays()
            )
        }

        if (duration.toHours().toInt() != 0) {
            concatString += "%02d hours ago"

            if (duration.toHours().toInt() == 1)
                concatString += "%02d hour ago"

            return Utils.formatStringToAgo(
                concatString, duration,
                duration.toHours()
            )

        }
        if (duration.toMinutes().toInt() != 0) {
            concatString += "%02d minutes ago"

            if (duration.toMinutes().toInt() == 1)
                concatString += "%02d minute ago"

            return Utils.formatStringToAgo(
                concatString, duration,
                duration.toMinutes()
            )

        }
        return ""
    }

}