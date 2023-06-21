package com.example.imu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bbgapp.imu.SensorIIO
import java.io.File
import kotlinx.android.synthetic.main.activity_main.tv_accelerometer
import kotlinx.android.synthetic.main.activity_main.tv_gyroscope
import kotlinx.android.synthetic.main.activity_main.tv_magnetometer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        setupCoroutine()
        setUpSensor()
    }

    override fun onPause() {
        super.onPause()
        stopCoroutine()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpSensor() {

        /** Print ACCELEROMETER **/
        coroutineScope.launch {
            while (true) {
                val x = SensorIIO.getValueAcceleromter().first
                val y = SensorIIO.getValueAcceleromter().second
                val z = SensorIIO.getValueAcceleromter().third

                tv_accelerometer.text = "$SENSOR_ACCELEROMETER \n" +
                        "\tx : $x \n" +
                        "\ty : $y \n" +
                        "\tz : $z \n"

                delay(100)
            }
        }

        /** Print GYROSCOPE **/
        coroutineScope.launch {
            while (true) {
                val x = SensorIIO.getValueGyroscope().first
                val y = SensorIIO.getValueGyroscope().second
                val z = SensorIIO.getValueGyroscope().third

                tv_gyroscope.text = "$SENSOR_GYROSCOPE \n" +
                        "\tx : $x \n" +
                        "\ty : $y \n" +
                        "\tz : $z \n"

                delay(100)
            }
        }

        /** Print MAGNETIC_FIELD **/
        coroutineScope.launch {
            while (true) {
                val x = SensorIIO.getValueMagnetometer().first
                val y = SensorIIO.getValueMagnetometer().second
                val z = SensorIIO.getValueMagnetometer().third

                tv_magnetometer.text = "$SENSOR_MAGNETIC_FIELD \n" +
                        "\tx : $x \n" +
                        "\ty : $y \n" +
                        "\tz : $z \n"

                delay(100)
            }
        }
    }

    private fun setupCoroutine() {
        stopCoroutine()

        val parentJob = Job()
        coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
    }

    private fun stopCoroutine() {
        if (this::coroutineScope.isInitialized) {
            coroutineScope.cancel()
        }
    }
    
    companion object {
        private const val SENSOR_ACCELEROMETER = "Sensor Accelerometer:"
        private const val SENSOR_GYROSCOPE = "Sensor Gyroscope:"
        private const val SENSOR_MAGNETIC_FIELD = "Sensor Magnetometer:"
    }
}