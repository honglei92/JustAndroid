package com.boco.whl.rxjavademo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 日志打印工具
 * <p/>
 * 包含了异常打印、日志打印、安卓Toast提示等方法
 */
public class PrintfUT {

    private static PrintfUT printfUT = null;

    private final String TAG_I = "APP_INFO";
    private final String TAG_W = "APP_WARN";
    private final String TAG_E = "APP_ERROR";
    private final String TAG_D = "APP_DEBUG";
    private final String TAG_V = "APP_VERBOSE";
    private boolean isDebug = true;
    private Toast toast = null;

    private PrintfUT() {
    }

    public static PrintfUT getInstance() {
        if (printfUT == null) {
            printfUT = new PrintfUT();
        }
        return printfUT;
    }

    /**
     * ===================================================================
     * Java异常打印
     *
     * @param e 异常类
     * @return 异常堆栈内容
     */
    public String getExceptionInfoByStackTraceElement(Exception e) {
        String sOut = "";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }

    public String getExceptionInfoByPrintStream(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        e.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return ret;
    }

    private String getExceptionInfoByPrintWriter(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    /**
     * ===================================================================
     * Android吐司提示打印
     *
     * @param context 上下文
     * @param Msg     打印内容
     */
    public void showShortToast(Context context, String Msg) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.cancel();
        toast = Toast.makeText(context, Msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showLongToast(Context context, String Msg) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.cancel();
        toast = Toast.makeText(context, Msg, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * ===================================================================
     * Android日志控制台打印
     */
    public void LogV(String Msg) {
        if (isDebug)
            Log.v(TAG_V, Msg);
    }

    public void LogV(Throwable throwable) {
        if (isDebug)
            LogV(Log.getStackTraceString(throwable));
    }

    public void LogV(Context context, String Msg) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogV(Msg);
            else
                LogV(context.getClass().getSimpleName() + "\n" + Msg);
    }

    public void LogV(String Msg, Throwable throwable) {
        if (isDebug)
            LogV(Msg + '\n' + Log.getStackTraceString(throwable));
    }

    public void LogV(Context context, String Msg, Throwable throwable) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogV(Msg, throwable);
            else
                LogV(context.getClass().getSimpleName() + "\n" + Msg, throwable);
    }

    public void LogD(String Msg) {
        if (isDebug)
            Log.d(TAG_D, Msg);
    }

    public void LogD(Throwable throwable) {
        if (isDebug)
            LogD(Log.getStackTraceString(throwable));
    }

    public void LogD(Context context, String Msg) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogD(Msg);
            else
                LogD(context.getClass().getSimpleName() + "\n" + Msg);
    }

    public void LogD(String Msg, Throwable throwable) {
        if (isDebug)
            LogD(Msg + '\n' + Log.getStackTraceString(throwable));
    }

    public void LogD(Context context, String Msg, Throwable throwable) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogD(Msg, throwable);
            else
                LogD(context.getClass().getSimpleName() + "\n" + Msg, throwable);
    }

    public void LogE(String Msg) {
        if (isDebug)
            Log.e(TAG_E, Msg);
    }

    public void LogE(Throwable throwable) {
        if (isDebug)
            LogE(Log.getStackTraceString(throwable));
    }

    public void LogE(Context context, String Msg) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogE(Msg);
            else
                LogE(context.getClass().getSimpleName() + "\n" + Msg);
    }

    public void LogE(String Msg, Throwable throwable) {
        if (isDebug)
            LogE(Msg + '\n' + Log.getStackTraceString(throwable));
    }

    public void LogE(Context context, String Msg, Throwable throwable) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogE(Msg, throwable);
            else
                LogE(context.getClass().getSimpleName() + "\n" + Msg, throwable);
    }

    public void LogW(String Msg) {
        if (isDebug)
            Log.w(TAG_W, Msg);
    }

    public void LogW(Throwable throwable) {
        if (isDebug)
            LogW(Log.getStackTraceString(throwable));
    }

    public void LogW(Context context, String Msg) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogW(Msg);
            else
                LogW(context.getClass().getSimpleName() + "\n" + Msg);
    }

    public void LogW(String Msg, Throwable throwable) {
        if (isDebug)
            LogW(Msg + '\n' + Log.getStackTraceString(throwable));
    }

    public void LogW(Context context, String Msg, Throwable throwable) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogW(Msg, throwable);
            else
                LogW(context.getClass().getSimpleName() + "\n" + Msg, throwable);
    }

    public void LogI(String Msg) {
        if (isDebug)
            Log.i(TAG_I, Msg);
    }

    public void LogI(Throwable throwable) {
        if (isDebug)
            LogI(Log.getStackTraceString(throwable));
    }

    public void LogI(Context context, String Msg) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogI(Msg);
            else
                LogI(context.getClass().getSimpleName() + "\n" + Msg);
    }

    public void LogI(String Msg, Throwable throwable) {
        if (isDebug)
            LogI(Msg + '\n' + Log.getStackTraceString(throwable));
    }

    public void LogI(Context context, String Msg, Throwable throwable) {
        if (isDebug)
            if (context == null || context.getClass() == null || context.getClass().getSimpleName() == null)
                LogI(Msg, throwable);
            else
                LogI(context.getClass().getSimpleName() + "\n" + Msg, throwable);
    }
}

