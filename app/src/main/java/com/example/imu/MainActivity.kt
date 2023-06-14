package com.example.imu

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.tv_accelerometer
import kotlinx.android.synthetic.main.activity_main.tv_gyroscope
import kotlinx.android.synthetic.main.activity_main.tv_magnetometer


class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var gyroscopeSensor: Sensor
    private lateinit var accelerometerSensor: Sensor
    private lateinit var magnetometerSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        setUpSensor()
    }

    private fun setUpSensor() {

        val sensorListenerGyroscope: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                tv_gyroscope.setText(
                    "Sensor Gyroscope: \n" +
                            "\tx : $x \n" +
                            "\ty : $y \n" +
                            "\tz : $z \n"
                )

                Log.i("sensor-gyroscope:", "x : $x , y : $y , z : $z")
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        val sensorListenerAccelerometer: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                tv_accelerometer.setText(
                    "Sensor Accelerometer: \n" +
                        "\tx : $x \n" +
                        "\ty : $y \n" +
                        "\tz : $z \n"
                )
                Log.i("sensorAccelerometer:", "x : $x , y : $y , z : $z")
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        val sensorListenerMagnetometer: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                tv_magnetometer.setText(
                    "Sensor Magnetometer: \n" +
                            "\tx : $x \n" +
                            "\ty : $y \n" +
                            "\tz : $z \n"
                )
                Log.i("sensorMagnetometer:", "x : $x , y : $y , z : $z")
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        sensorManager.registerListener(sensorListenerGyroscope, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorListenerAccelerometer, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorListenerMagnetometer, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
}