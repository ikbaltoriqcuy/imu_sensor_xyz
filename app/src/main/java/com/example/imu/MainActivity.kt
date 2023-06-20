package com.example.imu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                var scale = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_SCALE"))
                val x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_X"))
                val y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_Y"))
                val z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_Z"))

                scale = scale.ifEmpty { "1" }

                tv_accelerometer.text = "$SENSOR_ACCELEROMETER \n" +
                        "\tx : ${x.toInt() * scale.toFloat()} \n" +
                        "\ty : ${y.toInt() * scale.toFloat()} \n" +
                        "\tz : ${z.toInt() * scale.toFloat()} \n"

                delay(100)
            }
        }

        /** Print GYROSCOPE **/
        coroutineScope.launch {
            while (true) {
                var scale = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_SCALE"))
                val x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_X"))
                val y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_Y"))
                val z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_Z"))

                scale = scale.ifEmpty { "1" }

                tv_gyroscope.text = "$SENSOR_GYROSCOPE \n" +
                        "\tx : ${x.toInt() * scale.toFloat()} \n" +
                        "\ty : ${y.toInt() * scale.toFloat()} \n" +
                        "\tz : ${z.toInt() * scale.toFloat()} \n"

                delay(100)
            }
        }

        /** Print MAGNETIC_FIELD **/
        coroutineScope.launch {
            while (true) {
                var scale = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_SCALE"))
                val x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_X"))
                val y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_Y"))
                val z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_Z"))

                scale = scale.ifEmpty { "1" }

                tv_magnetometer.text = "$SENSOR_MAGNETIC_FIELD \n" +
                        "\tx : ${x.toInt() * scale.toFloat()} \n" +
                        "\ty : ${y.toInt() * scale.toFloat()} \n" +
                        "\tz : ${z.toInt() * scale.toFloat()} \n"

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

        private const val ROOT_PATH = "sys/bus/iio/devices"
        private const val PATH_DEVICE_1 = "/iio:device1"
        private const val PATH_DEVICE_2 = "/iio:device2"

        private const val ACCELEROMETER_FILE_SCALE = "/in_accel_scale"
        private const val ACCELEROMETER_FILE_X = "/in_accel_x_raw"
        private const val ACCELEROMETER_FILE_Y = "/in_accel_y_raw"
        private const val ACCELEROMETER_FILE_Z = "/in_accel_z_raw"

        private const val GYROSCOPE_FILE_SCALE = "/in_anglvel_scale"
        private const val GYROSCOPE_FILE_X = "/in_anglvel_x_raw"
        private const val GYROSCOPE_FILE_Y = "/in_anglvel_y_raw"
        private const val GYROSCOPE_FILE_Z = "/in_anglvel_z_raw"

        private const val MAGNETIC_FIELD_FILE_SCALE = "/in_magn_scale"
        private const val MAGNETIC_FIELD_FILE_X = "/in_magn_x_raw"
        private const val MAGNETIC_FIELD_FILE_Y = "/in_magn_y_raw"
        private const val MAGNETIC_FIELD_FILE_Z = "/in_magn_z_raw"
    }
}