package com.example.camera_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {
    CodeScanner mCodeScanner;
    TextView textview_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        CodeScannerView scannerView = findViewById(R.id.scanner_view_qrcode);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(qrcode_decode);
        scannerView.setOnClickListener(example);
    }

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

    //permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grant_Results) {
        super.onRequestPermissionsResult(requestCode, permissions, grant_Results);
        if (requestCode == 1) {
            if (grant_Results.length > 0 && grant_Results[0] != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "Always Refuse, must open permissions by User", Toast.LENGTH_LONG).show();
                    Uri uri_pack = Uri.fromParts("package", getPackageName(), null);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(uri_pack);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Refuse", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Allow", Toast.LENGTH_LONG).show();
            }
        }
    }
}