package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aidlserver.IDog;
import com.example.aidlserver.IGetMsg;
import com.example.aidlserver.Message2;
import com.example.aidlserver.User;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IDog baseService;

    private IGetMsg customService;
    private static User[] users = new User[] {
            new User(0, "jack0", "99999999990",""),
            new User(1, "jack0", "99999999990","")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);

        findViewById(R.id.start_c).setOnClickListener(this);
        findViewById(R.id.get_c).setOnClickListener(this);
        findViewById(R.id.stop_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                start();
                break;
            case R.id.get:
                get();
                break;
            case R.id.stop:
                stop();
                break;
            case R.id.start_c:
                start_custom();
                break;
            case R.id.get_c:
                get_custom();
                break;
            case R.id.stop_c:
                stop_custom();
                break;
        }
    }

    private ServiceConnection connBase = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            baseService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IDog.Stub.asInterface，获取接口
            baseService = IDog.Stub.asInterface(service);
        }
    };

    public void start() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidlserver.BASE_SERVICE");
        bindService(intent, connBase, BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "开始绑定服务", Toast.LENGTH_SHORT).show();
    }

    public void get() {
        try {
            if (baseService != null) {
                StringBuilder sBuilder = new StringBuilder();
                sBuilder.append("name:" + baseService.getName());
                sBuilder.append("\nage:" + baseService.getAge());
                Toast.makeText(MainActivity.this, sBuilder.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "请先绑定服务", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (connBase != null) {
            unbindService(connBase);
            Toast.makeText(MainActivity.this, "服务解除绑定", Toast.LENGTH_SHORT).show();
        }
    }





    private ServiceConnection customConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            customService = IGetMsg.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            customService = null;
        }
    };
    public void start_custom() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidlserver.CUSTOM_SERVICE");
        bindService(intent, customConn, BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "开始绑定服务", Toast.LENGTH_SHORT).show();
    }

    public void get_custom() {
        try {
            Random random=new Random();
            int nextInt=random.nextInt(2);
            List<Message2> msgs=customService.getMsg(users[nextInt]);
            StringBuilder sBuilder=new StringBuilder();
            for(Message2 msg:msgs){
                sBuilder.append(msg.toString()+"\n");
            }
            Toast.makeText(MainActivity.this, sBuilder.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "获取数据出错", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void stop_custom() {
        unbindService(customConn);
        Toast.makeText(MainActivity.this, "解除绑定服务成功", Toast.LENGTH_SHORT).show();
    }
}
