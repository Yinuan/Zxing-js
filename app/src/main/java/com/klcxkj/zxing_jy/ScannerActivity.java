package com.klcxkj.zxing_jy;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.klcxkj.jxing.OnScannerCompletionListener;
import com.klcxkj.jxing.ScannerView;
import com.klcxkj.jxing.common.Scanner;


/**
 * 扫描
 */
public class ScannerActivity extends AppCompatActivity implements OnScannerCompletionListener {



    public static final int EXTRA_LASER_LINE_MODE_0 = 0;
    public static final int EXTRA_LASER_LINE_MODE_1 = 1;
    public static final int EXTRA_LASER_LINE_MODE_2 = 2;


    private ScannerView mScannerView;
    private Result mLastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);


        mScannerView = (ScannerView) findViewById(R.id.scanner_view);
        mScannerView.setOnScannerCompletionListener(this);


        int laserMode = 0;
        int scanMode = 0;

        mScannerView.setMediaResId(R.raw.weixin_beep);//设置扫描成功的声音
        mScannerView.setDrawText("将二维码放入框内", true);
      //  mScannerView.setLaserFrameBoundColor(R.color.base_color);

        if (scanMode == 1) {
            //二维码
            mScannerView.setScanMode(Scanner.ScanMode.QR_CODE_MODE);
        } else if (scanMode == 2) {
            //一维码
            mScannerView.setScanMode(Scanner.ScanMode.PRODUCT_MODE);
        }
        //显示扫描成功后的缩略图
        mScannerView.isShowResThumbnail(true);
        //全屏识别
        mScannerView.isScanFullScreen(false);
        //隐藏扫描框
        mScannerView.isHideLaserFrame(false);
//        mScannerView.isScanInvert(true);//扫描反色二维码
//        mScannerView.setCameraFacing(CameraFacing.FRONT);
//        mScannerView.setLaserMoveSpeed(1);//速度

//        mScannerView.setLaserFrameTopMargin(100);//扫描框与屏幕上方距离
//        mScannerView.setLaserFrameSize(400, 400);//扫描框大小
//        mScannerView.setLaserFrameCornerLength(25);//设置4角长度
//        mScannerView.setLaserLineHeight(5);//设置扫描线高度
//        mScannerView.setLaserFrameCornerWidth(5);

        switch (laserMode) {
            case EXTRA_LASER_LINE_MODE_0:
                mScannerView.setLaserLineResId(R.drawable.scan_line);//线图
                break;
            case EXTRA_LASER_LINE_MODE_1:
                mScannerView.setLaserGridLineResId(R.drawable.zfb_grid_scan_line);//网格图
                mScannerView.setLaserFrameBoundColor(0xFF26CEFF);//支付宝颜色
                break;
            case EXTRA_LASER_LINE_MODE_2:
                mScannerView.setLaserColor(Color.RED);
                break;
        }
    }


    @Override
    protected void onResume() {
        mScannerView.onResume();
        resetStatusView();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mLastResult != null) {
                    restartPreviewAfterDelay(0L);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void restartPreviewAfterDelay(long delayMS) {
        mScannerView.restartPreviewAfterDelay(delayMS);
        resetStatusView();
    }

    private void resetStatusView() {
        mLastResult = null;
    }


    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        if (rawResult == null) {
            Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("ScannerActivity", rawResult.toString());
    }
}
