# AndroidJar_MyInstallApkLibrary
我自制的安装APK的Jar包  
注意：测试过 不用替换该代码中的包名就可以正常使用。  
下面是Unity端调用代码示例
```
#region 安装APK的代码
        private bool isInitMyInstallApkLibrary = false;
        private AndroidJavaObject myInstallApkLibrary;

        private void InitMyInstallApkLibrary()
        {
            AndroidJavaClass otherClass = new AndroidJavaClass("com.example.myinstallapk.MainActivity");
            myInstallApkLibrary = otherClass.CallStatic<AndroidJavaObject>("getInstance");

            myInstallApkLibrary.Call("InitContext", currActivity);

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
