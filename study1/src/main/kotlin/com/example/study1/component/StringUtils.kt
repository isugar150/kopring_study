package com.example.study1.component

import java.io.PrintWriter
import java.io.StringWriter



class StringUtils {
    fun getStackTrace(t: Throwable): String {
        val sw = StringWriter()
        t.printStackTrace(PrintWriter(sw))
        return sw.toString()
    }
}