package com.example.thanhvip123wallpaper.workmanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkRequestManager {

    fun startUploadImage(url: String , context: Context) {        val workConstraints = Constraints.Builder().setRequiredNetworkType(
            NetworkType.UNMETERED
        ).setRequiresCharging(true).build()
        val uploadRequest = OneTimeWorkRequestBuilder<UploadImageWorker>().setExpedited(
            OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
        ).setInitialDelay(10 , TimeUnit.MINUTES).setConstraints(workConstraints).build()
        WorkManager.getInstance(context).enqueue(uploadRequest)
    }
}