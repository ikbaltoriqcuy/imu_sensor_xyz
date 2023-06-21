package com.bbgapp.imu

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

/**
Created by ikbaltoriq on 19,June,2023
 **/
object FileReader {

    fun readFileContent(file: File): String {
        val content = StringBuilder()
        try {
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            var line: String?
            while (br.readLine().also { line = it } != null) {
                content.append(line)
                content.append("\n")
            }
            br.close()
            isr.close()
            fis.close()
        } catch (e: IOException) { e.printStackTrace() }
        return content.trim().toString()
    }

}