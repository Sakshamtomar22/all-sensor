package com.example.anew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope, lightSensor;
    private TextView accelerometerData, gyroscopeData, lightSensorData;

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    accelerometerData.setText("Accelerometer Data: \n" +
                            "X: " + event.values[0] + "\n" +
                            "Y: " + event.values[1] + "\n" +
                            "Z: " + event.values[2]);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    gyroscopeData.setText("Gyroscope Data: \n" +
                            "X: " + event.values[0] + "\n" +
                            "Y: " + event.values[1] + "\n" +
                            "Z: " + event.values[2]);
                    break;
                case Sensor.TYPE_LIGHT:
                    lightSensorData.setText("Light Sensor Data: " + event.values[0]);
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something if sensor accuracy changes
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerData = findViewById(R.id.accelerometerData);
        gyroscopeData = findViewById(R.id.gyroscopeData);
        lightSensorData = findViewById(R.id.lightSensorData);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
