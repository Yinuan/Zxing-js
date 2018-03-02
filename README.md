# Zxing-js
二维码扫码工具
    
   1.使用
   
    project中的build
    	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

  2.导入 
  
    compile 'com.github.Yinuan:Zxing-js:v1.3'
  
  3.控件
  
      <com.klcxkj.jxing.ScannerView
        android:id="@+id/scanner_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        
   4.设置属性以及监听回调
   
    mScannerView.setOnScannerCompletionListener(this);
