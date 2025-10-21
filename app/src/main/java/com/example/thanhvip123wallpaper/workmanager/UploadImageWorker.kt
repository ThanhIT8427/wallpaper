package com.example.thanhvip123wallpaper.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadImageWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        uploadImage()
        return Result.success()
    }

    fun uploadImage() {

    }
}