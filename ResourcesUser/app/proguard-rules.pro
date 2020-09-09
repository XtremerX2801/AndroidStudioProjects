# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class androidx.lifecycle.AndroidViewModel{*;}
-keep class androidx.lifecycle.AbstractSavedStateViewModelFactory{*;}
-keep class androidx.lifecycle.ClassesInfoCache{*;}
-keep class androidx.lifecycle.CompositeGeneratedAdaptersObserver{*;}
-keep class androidx.lifecycle.ComputableLiveData{*;}
-keep class androidx.lifecycle.EmptyActivityLifecycleCallbacks{*;}
-keep class androidx.lifecycle.FullLifecycleObserver{*;}
-keep class androidx.lifecycle.FullLifecycleObserverAdapter{*;}
-keep class androidx.lifecycle.GeneratedAdapter{*;}
-keep class androidx.lifecycle.HasDefaultViewModelProviderFactory{*;}
-keep class androidx.lifecycle.Lifecycle{*;}
-keep class androidx.lifecycle.LifecycleDispatcher{*;}
-keep class androidx.lifecycle.LifecycleEventObserver{*;}
-keep class androidx.lifecycle.LifecycleObserver{*;}
-keep class androidx.lifecycle.LifecycleOwner{*;}
-keep class androidx.lifecycle.LifecycleRegistry{*;}
-keep class androidx.lifecycle.LifecycleService{*;}
-keep class androidx.lifecycle.Lifecycling{*;}
-keep class androidx.lifecycle.LiveData{*;}
-keep class androidx.lifecycle.MediatorLiveData{*;}
-keep class androidx.lifecycle.MethodCallsLogger{*;}
-keep class androidx.lifecycle.MutableLiveData{*;}
-keep class androidx.lifecycle.Observer{*;}
-keep class androidx.lifecycle.OnLifecycleEvent{*;}
-keep class androidx.lifecycle.ProcessLifecycleOwner{*;}
-keep class androidx.lifecycle.ProcessLifecycleOwnerInitializer{*;}
-keep class androidx.lifecycle.ReflectiveGenericLifecycleObserver{*;}
-keep class androidx.lifecycle.R{*;}
-keep class androidx.lifecycle.ReportFragment{*;}
-keep class androidx.lifecycle.SavedStateHandle{*;}
-keep class androidx.lifecycle.SavedStateHandleController{*;}
-keep class androidx.lifecycle.SavedStateViewModelFactory{*;}
-keep class androidx.lifecycle.ServiceLifecycleDispatcher{*;}
-keep class androidx.lifecycle.SingleGeneratedAdapterObserver{*;}
-keep class androidx.lifecycle.Transformations{*;}
-keep class androidx.lifecycle.ViewModel{*;}
-keep class androidx.lifecycle.ViewModelProvider{*;}
-keep class androidx.lifecycle.ViewModelStore{*;}
-keep class androidx.lifecycle.ViewModelStoreOwner{*;}