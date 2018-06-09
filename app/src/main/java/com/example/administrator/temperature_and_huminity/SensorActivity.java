package com.example.administrator.temperature_and_huminity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {

    private TextView tvSensors,tvSensors1,tvSensors2;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        //显示数据控件
        tvSensors = (TextView) findViewById(R.id.tvSensors);
        tvSensors1 = (TextView) findViewById(R.id.tvSensors1);
        tvSensors2 = (TextView) findViewById(R.id.tvSensors2);
        // 获取传感器服务
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // 当传感器的精度发生改变时调用此方法

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_LIGHT:// 光照传感器
                tvSensors.setText("光照\n"+String.valueOf(event.values[0])+"lx");
                break;
            case Sensor.TYPE_PRESSURE:// 气压传感器
                tvSensors1.setText("气压\n"+String.valueOf((event.values[0]))+"mbar");
                break;
            case Sensor.TYPE_TEMPERATURE:// 温度传感器
                tvSensors2.setText("温度\n"+String.valueOf((event.values[0]))+"°C");
                break;
            default:
                break;
        }
    }

    //生命周期函数
    @Override
    protected void onResume() {
        super.onResume();
        // 注册监听传感器
        // 光照传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);
        // 气压传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_UI);
        // 温度传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),
                SensorManager.SENSOR_DELAY_UI);
    }

    //生命周期函数
    @Override
    protected void onPause() {
        super.onPause();
        // 取消传感器监听
        sensorManager.unregisterListener(this);
    }

}