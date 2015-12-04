package com.haikuowuya.core.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * 获取手机设备的IMEI等信息
 */
public class AndroidUtils
{
    private AndroidUtils()
    {
        throw new Error("AndroidUtils can not  instantiate!");
    }

    /**
     * 获取手机设备IMEI号
     *
     * @param context :上下文对象
     * @return string
     */
    public static String getDeviceId(Context context)
    {
        String deviceId = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = tm.getDeviceId();
        return deviceId;
    }

    /**
     * 获取手机型号
     *
     * @return string
     */
    public static String getPhoneModel()
    {
        return android.os.Build.MODEL;
    }

    /**
     * 获取系统发布版本
     *
     * @return String
     */
    public static String getOSVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取基带版本
     *
     * @return string
     */
    public static String getBaseHandVersion()
    {
        String BaseHandVersion = "";
        try
        {

            Class<?> cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();

            Method m = cl.getMethod("get", new Class[]{String.class, String.class});

            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", ""});

            BaseHandVersion = (String) result;

        } catch (Exception e)
        {

        }
        return BaseHandVersion;
    }

    /**
     * 获取系统内核
     *
     * @return String
     */
    public static String getOSKernelVersion()
    {
        String oSKernelVersion = "";
        Process process = null;
        try
        {
            process = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException e)
        {

            e.printStackTrace();
        }
        // get the output line
        InputStream outs = process.getInputStream();
        InputStreamReader isrout = new InputStreamReader(outs);
        BufferedReader brout = new BufferedReader(isrout, 8 * 1024);
        String result = "";
        String line;
        try
        {
            while ((line = brout.readLine()) != null)
            {
                result += line;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (result != "")
        {
            String Keyword = "version ";
            int index = result.indexOf(Keyword);
            line = result.substring(index + Keyword.length());
            index = line.indexOf(" ");
            oSKernelVersion = line.substring(0, index);
        }
        return oSKernelVersion;
    }

    /**
     * 获取硬件版本信息
     *
     * @return String
     */
    public static String getHardWareVersion()
    {
        String result = "";
        String str = "";
        ProcessBuilder cmd;
        try
        {
            String[] args = {"system/bin/cat", "proc/cpuinfo"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) != -1)
            {
                result += new String(re);
            }
            in.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            int start = result.indexOf("Hardware");
            int end = result.indexOf("Revision");
            String str1 = result.substring(start, end);
            int offest = str1.indexOf(':');
            str = str1.substring(offest + 1).replaceAll("\n", "");
        } catch (Exception e)
        {
            return "";
        }

        return str;

    }

    /**
     * 获取应用版本名称
     *
     * @param context context
     * @return String
     */
    public static String getVersionName(Context context)
    {
        String versionName = "";
        try
        {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // 当前应用的版本名称
            versionName = info.versionName;

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 得到应用版本号
     *
     * @param context 上下文
     * @return 得到应用版本号
     */
    public static int getVersionCode(Context context)
    {
        int versionCode = -1;
        try
        {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // 当前应用的版本号
            versionCode = info.versionCode;

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionCode;

    }

    /**
     * 获取应用程序唯一名字
     *
     * @param context context
     * @return String
     */
    public static String getAPPName(Context context)
    {
        String appName = "";
        try
        {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            appName = info.packageName + "." + info.applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return appName;
        }
        return appName;
    }

}
