package com.zony.imageload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.zony.imageload.fragment.GlideBaseFragment;
import com.zony.imageload.fragment.RecycleViewFragment;
import com.zony.imageload.fragment.TrancformFragment;
import com.zony.imageload.utils.ImgLoadUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        changerFragment(R.id.navigation_home);
        initPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImgLoadUtil.clearAll(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        changerFragment(item.getItemId());
        return true;
    }

    private void changerFragment(int itemId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (itemId) {
            case R.id.navigation_home:
                ft.replace(R.id.content,
                        GlideBaseFragment.newInstance(getString(R.string.hello_blank_fragment), "1"));
                break;
            case R.id.navigation_dashboard:
                ft.replace(R.id.content,
                        RecycleViewFragment.newInstance(getString(R.string.recycleview_fragment), "2"));
                break;
            case R.id.navigation_notifications:
                ft.replace(R.id.content,
                        TrancformFragment.newInstance(getString(R.string.tranceform_fragment), "3"));
                break;
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {
                Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET, Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this,
                    perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}
