package com.orangeaterz.xposedtest;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedTest implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("handleLoadPackage执行啦:package:" + lpparam.packageName);
//        XposedBridge.log("handleLoadPackage执行啦!");
        if (lpparam.packageName.equals("com.orangeaterz.designpatterns")) {

            XposedBridge.log("开始hook测试程序！");
            XposedHelpers.findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("处理setText方法前");
                    param.args[0] = "我是被Xposed修改的";
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("处理setText方法后");
                }
            });

        }
    }
}
