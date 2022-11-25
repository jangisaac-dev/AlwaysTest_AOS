package com.jangisaac.alwaystest

import android.util.Log

/**
 * Created By isaacjang on 2022/11/25
 */
object DLog {
    private const val filter_string = "JIS_"
    private const val VERBOSE = 2
    private const val DEBUG = 3
    private const val INFO = 4
    private const val WARN = 5
    private const val ERROR = 6
    private const val ASSERT = 7
    fun v(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) Log.v(tag, msg!!)
    }

    fun vv() {
        log(VERBOSE, "")
    }

    fun vv(msg: String) {
        log(VERBOSE, msg)
    }

    fun vv(tag: String, msg: String) {
        log(VERBOSE, tag, msg)
    }

    fun d(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) Log.d(tag, msg!!)
    }

    fun dd() {
        log(DEBUG, "")
    }

    fun dd(msg: String) {
        log(DEBUG, msg)
    }

    fun dd(tag: String, msg: String) {
        log(DEBUG, tag, msg)
    }

    fun i(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) Log.i(tag, msg!!)
    }

    fun ii() {
        log(INFO, "")
    }

    fun ii(msg: String) {
        log(INFO, msg)
    }

    fun ii(tag: String, msg: String) {
        log(INFO, tag, msg)
    }

    fun w(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) Log.w(tag, msg!!)
    }

    fun ww() {
        log(WARN, "")
    }

    fun ww(msg: String) {
        log(WARN, msg)
    }

    fun ww(tag: String, msg: String) {
        log(WARN, tag, msg)
    }

    fun e(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) Log.e(tag, msg!!)
    }

    fun ee() {
        log(ERROR, "")
    }

    fun ee(msg: String) {
        log(ERROR, msg)
    }

    fun ee(tag: String, msg: String) {
        log(ERROR, tag, msg)
    }

    private fun log(priority: Int, msg: String) {
        if (BuildConfig.DEBUG) {
            val msgBuilder = StringBuilder()
            msgBuilder
                .append("(").append(Thread.currentThread().stackTrace[4].fileName).append(":")
                .append(Thread.currentThread().stackTrace[4].lineNumber).append(")")
                .append(" [").append(Thread.currentThread().stackTrace[4].methodName).append("()")
                .append("]").append(" :")
                .append(" [").append(msg).append("]")
            val tag = Thread.currentThread().stackTrace[4].fileName.replace(".java", "")
                .replace(".kt", "")
            printLog(priority, tag, msgBuilder.toString())
        }
    }

    private fun log(priority: Int, tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            val msgBuilder = StringBuilder()
            msgBuilder
                .append("(").append(Thread.currentThread().stackTrace[4].fileName).append(":")
                .append(Thread.currentThread().stackTrace[4].lineNumber)
                .append(")") //                    .append("[").append(Thread.currentThread().getStackTrace()[4].getFileName().replace(".java", "").replace(".kt", ""))
                .append(" :: ").append(Thread.currentThread().stackTrace[4].methodName).append("()")
                .append("]").append(" :")
                .append(" [").append(msg).append("]")
            printLog(priority, tag, msgBuilder.toString())
        }
    }

    private fun printLog(priority: Int, tag: String, strMssage: String) {
//        if(strMssage.length() > 2000) {
//            Log.println(priority, filter_string + tag, strMssage.substring(0, 2000));
//            printLog(priority, tag, strMssage.substring(2000));
//        } else {
        Log.println(priority, filter_string + tag, strMssage)
        //        }
    }

    fun getFileName(): String {
        return Thread.currentThread().stackTrace[3].fileName
    }

    fun getClassName(): String {
        return Thread.currentThread().stackTrace[3].className
    }

    fun getMethodName(): String {
        return Thread.currentThread().stackTrace[3].methodName
    }

    fun getLineInfo(): String {
        val msgBuilder = StringBuilder()
        msgBuilder.append(Thread.currentThread().stackTrace[3].fileName).append(":")
            .append(Thread.currentThread().stackTrace[3].lineNumber)
        return msgBuilder.toString()
    }
}
