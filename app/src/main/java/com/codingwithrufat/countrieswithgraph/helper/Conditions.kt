package com.codingwithrufat.countrieswithgraph.helper

import android.content.Context
import android.content.res.Configuration

fun checkLandscapeMode(context: Context): Boolean = context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE