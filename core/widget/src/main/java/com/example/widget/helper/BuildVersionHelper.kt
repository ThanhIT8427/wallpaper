package com.example.widget.helper

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object BuildVersionHelper {
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    fun isBuildBiggerThanS(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    fun isBuildBiggerThanTiramisu(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}