package com.example.myinstallapk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

public class MainActivity {

    private static MainActivity instance;
    private Context mContext;

    public static MainActivity getInstance() {
        if (instance == null)
            instance = new MainActivity();
        Log.i("Unity", "getInstance " + instance.toString());
        return instance;
    }

    public void InitContext(Context context) {
        mContext = context;
        Log.i("Unity", "InitContext mContext：" + mContext.getPackageName());
    }

    public void installApk(String path) {
        Log.i("Unity", "开始installApk path：" + path);
        File file = new File(path);
        Log.i("Unity", "安装的file：" + file.getAbsolutePath() + " name:" + file.getName() + " Exit:" + file.exists());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Log.i("Unity", "intent：" + intent.getFlags());

        if (Build.VERSION.SDK_INT >= 24) { //Android 7.0及以上
            // 参数2 清单文件中provider节点里面的authorities ; 参数3  共享的文件,即apk包的file类
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.zhijianar.yinmi.fileprovider", file);//记住修改包名

            //对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");

            Log.i("Unity", "Android 7.0及以上  intent：" + intent.getPackage());
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

            Log.i("Unity", "  Uri：" + Uri.fromFile(file) + " intent：" + intent.getAction());
        }

        //Log.i("Unity", "startActivity  intent：" + intent.getAction());
        //Log.i("Unity", "InitCompareApi mContext：" + mContext.getPackageName());
        mContext.startActivity(intent);
        Log.i("Unity", "安装结束！");
    }

}
