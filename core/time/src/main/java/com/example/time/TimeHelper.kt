package com.example.time

import java.time.LocalDate
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


object TimeHelper {
    fun checkLocalDate(formatter: DateTimeFormatter =
                           DateTimeFormatter.ofPattern("uuuu-MM-dd")) {
        val date = MonthDay.now()
        println("Date: $date")
        val text = date.format(formatter)
        println("Text: $text")
        val parsedDate = MonthDay.parse(text, formatter)
        println("ParsedDate: $parsedDate")

    }
}