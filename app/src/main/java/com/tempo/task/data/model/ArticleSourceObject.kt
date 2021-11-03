package com.tempo.task.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleSourceObject(
    val name: String = ""
) : Parcelable
