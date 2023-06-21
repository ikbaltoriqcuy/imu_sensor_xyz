package com.bbgapp.imu

import java.io.File

/**
 Created by ikbaltoriq on 21,June,2023
 **/

object SensorIIO {

    private const val ROOT_PATH = "sys/bus/iio/devices"
    private const val PATH_DEVICE_1 = "/iio:device1"
    private const val PATH_DEVICE_2 = "/iio:device2"

    private const val ACCELEROMETER_FILE_SCALE = "/in_accel_scale_available"
    private const val ACCELEROMETER_FILE_X = "/in_accel_x_raw"
    private const val ACCELEROMETER_FILE_Y = "/in_accel_y_raw"
    private const val ACCELEROMETER_FILE_Z = "/in_accel_z_raw"

    private const val GYROSCOPE_FILE_SCALE = "/in_anglvel_scale_available"
    private const val GYROSCOPE_FILE_X = "/in_anglvel_x_raw"
    private const val GYROSCOPE_FILE_Y = "/in_anglvel_y_raw"
    private const val GYROSCOPE_FILE_Z = "/in_anglvel_z_raw"

    private const val MAGNETIC_FIELD_FILE_SCALE = "/in_magn_scale"
    private const val MAGNETIC_FIELD_FILE_X = "/in_magn_x_raw"
    private const val MAGNETIC_FIELD_FILE_Y = "/in_magn_y_raw"
    private const val MAGNETIC_FIELD_FILE_Z = "/in_magn_z_raw"

    fun getValueAcceleromter(): Triple<Float, Float, Float> {
        var scale = 0.0f
        val scaleText = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_SCALE"))
        var x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_X"))
        var y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_Y"))
        var z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$ACCELEROMETER_FILE_Z"))

        scale = scaleText.split(" ")[0].ifEmpty { "0" }.toFloat()
        x = x.ifEmpty { "0" }
        y = y.ifEmpty { "0" }
        z = z.ifEmpty { "0" }

        return Triple(x.toInt() * scale , y.toInt() * scale, z.toInt() * scale)
    }

    fun getValueGyroscope(): Triple<Float, Float, Float> {
        var scale = 0.0f
        val scaleText = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_SCALE"))
        var x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_X"))
        var y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_Y"))
        var z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_1$GYROSCOPE_FILE_Z"))

        scale = scaleText.split(" ")[0].ifEmpty { "0" }.toFloat()
        x = x.ifEmpty { "0" }
        y = y.ifEmpty { "0" }
        z = z.ifEmpty { "0" }

        return Triple(x.toInt() * scale , y.toInt() * scale, z.toInt() * scale)
    }

    fun getValueMagnetometer(): Triple<Float, Float, Float> {
        var scale = 0.0f
        val scaleText = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_SCALE"))
        var x = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_X"))
        var y = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_Y"))
        var z = FileReader.readFileContent(File("$ROOT_PATH$PATH_DEVICE_2$MAGNETIC_FIELD_FILE_Z"))

        scale = scaleText.ifEmpty { "0" }.toFloat()
        x = x.ifEmpty { "0" }
        y = y.ifEmpty { "0" }
        z = z.ifEmpty { "0" }

        return Triple(x.toInt() * scale , y.toInt() * scale, z.toInt() * scale)
    }

}