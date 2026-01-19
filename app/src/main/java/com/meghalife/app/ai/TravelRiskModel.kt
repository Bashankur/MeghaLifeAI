package com.meghalife.app.ai

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TravelRiskModel(context: Context) {

    private val interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModel(context))
    }

    private fun loadModel(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("travel_risk_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset,
            declaredLength
        )
    }

    fun predict(
        month: Int,
        weather: Int,
        terrain: Int,
        roadAlert: Int
    ): RiskLevel {

        val input = arrayOf(
            floatArrayOf(
                month.toFloat(),
                weather.toFloat(),
                terrain.toFloat(),
                roadAlert.toFloat()
            )
        )

        val output = Array(1) { FloatArray(3) }

        interpreter.run(input, output)

        val predictedIndex =
            output[0].indices.maxBy { output[0][it] }

        return when (predictedIndex) {
            0 -> RiskLevel.LOW
            1 -> RiskLevel.MEDIUM
            else -> RiskLevel.HIGH
        }
    }
}
