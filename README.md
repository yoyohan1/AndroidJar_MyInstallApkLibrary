# MyInstallApkLibrary

#### 介绍
我自制的安装APK的Jar包 用于优路AR项目  
- 使用方式：    
调用安装Apk的方法 传入路径即可  
- 适用环境：    
安卓9.1以下全部适配  

#### Unity端 调用说明
```
#region 安装APK的Library
private bool isInitMyInstallApkLibrary = false;
private AndroidJavaObject myInstallApkLibrary;

private void InitMyInstallApkLibrary()
{
    AndroidJavaClass unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
    AndroidJavaObject currActivity = unityClass.GetStatic<AndroidJavaObject>("currentActivity");

    AndroidJavaClass compareClass = new AndroidJavaClass("com.example.myinstallapk.MainActivity");
    myInstallApkLibrary = compareClass.CallStatic<AndroidJavaObject>("getInstance");

    myInstallApkLibrary.Call("InitCompareApi", currActivity);

    isInitMyInstallApkLibrary = true;
}

public void MyInstallApkLibrary(string path)
{
#if UNITY_ANDROID && !UNITY_EDITOR
    if (isInitMyInstallApkLibrary == false)
        InitMyInstallApkLibrary();

    myInstallApkLibrary.Call("installApk", path);
#endif
}
#endregion
```