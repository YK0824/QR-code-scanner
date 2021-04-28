# QR-code-scanner
Camera Persission can see more in <https://github.com/YK0824/Android-Camera-Permission> 
In this case, the full screen of the screen was mainly adjusted, the status bar and title bar were hidden, and the function of QR code scanner was added.
# Full screen, Hide status bar and title bar
In your Style, add windowNoTitle, windowActionBar, android: windowFullscreen these settings, users can make adjustments according to their own needs.  
The full-screen method can be more beautiful, and you can also customize the colors and styles of the status bar and title bar.
# QR code Scanner
Mainly use third-party components. This supports Java and Kotlin. For details, please refer to the link below.  
Code scanner library for Android, based on ZXing.
* Auto focus and flash light control
* Portrait and landscape screen orientations
* Back and front facing cameras
* Customizable viewfinder
* Kotlin friendly
* Touch focus
See more: <https://github.com/yuriy-budiyev/code-scanner> 
###### In the Build
Add dependency.
```java
dependencies {
	implementation 'com.budiyev.android:code-scanner:2.1.0'
}
```
###### In the Activity Layout
Add CodeScannerView and start using, here is my Layout.
```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/p0_login_background"
    tools:context=".MainActivity">

    <com.budiyev.android.codescanner.CodeScannerView
        android:id="@+id/scanner_view_qrcode"
        android:layout_width="278dp"
        android:layout_height="266dp"
        android:layout_marginStart="66dp"
        android:layout_marginTop="88dp"
        android:layout_marginEnd="67dp"
        android:layout_marginBottom="377dp"
        app:autoFocusButtonColor="@android:color/white"
        app:autoFocusButtonVisible="true"
        app:flashButtonColor="@android:color/white"
        app:flashButtonVisible="true"
        app:frameAspectRatioHeight="1"
        app:frameAspectRatioWidth="1"
        app:frameColor="@android:color/white"
        app:frameCornersRadius="0dp"
        app:frameCornersSize="0dp"
        app:frameSize="0.75"
        app:frameThickness="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:maskColor="#77000000"></com.budiyev.android.codescanner.CodeScannerView>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.792" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
###### In the Activity.java
First define CodeScannerView and CodeScanner.  
And define trigger setDecodeCallback, setOnClickListener events.  
```java
        CodeScannerView scannerView = findViewById(R.id.scanner_view_qrcode);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(qrcode_decode);
        scannerView.setOnClickListener(example);
```
When the setDecodeCallback event is triggered, the message will be displayed on the Textviewer.    
```java
    //QR code decode
    private DecodeCallback qrcode_decode = new DecodeCallback() {
        @Override
        public void onDecoded(@NonNull Result result) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textview_qrcode.setText(result.getText());
                }
            });
        }
    };
```
If you want to scan QR Code repeatedly, here I am writing on ScannerView event of setOnClickListener.  
When you click ScannerView, it will rescan.
```java
    //Click Event
    private View.OnClickListener example = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.scanner_view_qrcode:
                    mCodeScanner.startPreview();
                    break;
            }
        }
    };
```
Finally, to show my pictures and features here.  
![image](https://github.com/YK0824/QR-code-scanner/blob/main/main.PNG)
