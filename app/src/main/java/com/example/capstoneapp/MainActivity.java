package com.example.capstoneapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private static final String serverIP = "172.20.10.2"; //serverIP를 추가합니다.
    private static final int port = 2807; //서버에서 설정한 UDP 포트번호를 추가합니다.
    private String msg="ON";
    private String return_msg;
    Switch aSwitch,tSwitch, switch1,switch2,switch3,switch4;

    public String run(String input) {
        try {
            byte[] sendData = new byte[1024];

// sendData에 대한 메세지 생성 작업 수행
            sendData=input.getBytes();
            String ip = "172.20.10.2";

            int port = 2807;

            DatagramSocket clientSocket = new DatagramSocket();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port);

            clientSocket.send(sendPacket);

            Log.d("UDP", "C: !!!!!!!!!!!!!!!!!!!");

        } catch (Exception ex) {
            Log.d("UDP", "C: Error", ex);
        }
        return return_msg;
    }
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        aSwitch=(Switch)findViewById(R.id.switch2);
        tSwitch=(Switch)findViewById(R.id.switch10);
        switch1=(Switch)findViewById(R.id.switch4);
        switch2=(Switch)findViewById(R.id.switch6);
        switch3=(Switch)findViewById(R.id.switch7);
        switch4=(Switch)findViewById(R.id.switch8);
        aSwitch.setChecked(true);
        tSwitch.setEnabled(false);
        switch1.setEnabled(false);
        switch2.setEnabled(false);
        switch3.setEnabled(false);
        switch4.setEnabled(false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==false){
                    tSwitch.setEnabled(true);
                    switch1.setEnabled(true);
                    //switch2.setEnabled(true);
                    //switch3.setEnabled(true);
                    //switch4.setEnabled(true);
                    tSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b==true){
                                run("ON");
                                tSwitch.setText("   전체 소등");
                                switch1.setChecked(true);
                                //switch2.setChecked(true);
                                //switch3.setChecked(true);
                                //switch4.setChecked(true);

                            }else{
                                run("OFF");
                                tSwitch.setText("   전체 점등");
                                switch1.setChecked(false);
                                //switch2.setChecked(false);
                                //switch3.setChecked(false);
                                //switch4.setChecked(false);
                            }
                        }
                    });
                }else{
                    tSwitch.setEnabled(false);
                    switch1.setEnabled(false);
                    switch2.setEnabled(false);
                    switch3.setEnabled(false);
                    switch4.setEnabled(false);
                }
            }
        });

    }

    //public MainActivity(){run();}
}
