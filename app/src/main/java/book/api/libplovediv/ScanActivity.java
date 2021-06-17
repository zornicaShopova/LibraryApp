package book.api.libplovediv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final int REQUEST_CODE = 333;
    SurfaceView surfaceView;
    TextView barcodeTV;
    Button okB, cancelB;
    String code;

    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    ToneGenerator toneGenerator;
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


        //sound when code is scaned
        toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, 50);

        //init();

    }

    @Override
    public void handleResult(Result result) {

        ReservationBooksActivity.scanResultTV.setText(result.getText().toString());
        Log.i("Barcode",""+result);

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }

    //    private void init() {
//        //reading the barcode
//        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
//        //camera
//        cameraSource = new CameraSource.Builder(this, barcodeDetector)
//                .setRequestedPreviewSize(1920, 1080).setAutoFocusEnabled(true).build();
//
//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(@NonNull SurfaceHolder holder) {
//                //if we have permission for ther camera then we can use it
//                try {
//                    if (ActivityCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA)
//                            == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(surfaceView.getHolder());
//                    } else {
//                        ActivityCompat.requestPermissions(ScanActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//
//                SparseArray<Barcode> barcodes = detections.getDetectedItems();
//
//                if (barcodes.size() != 0) {
//                    barcodeTV.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //display barcode
//                            barcodeTV.setText(barcodes.valueAt(0).displayValue);
//                        }
//                    });
//                }
//            }
//        });
//
//
//    }
}