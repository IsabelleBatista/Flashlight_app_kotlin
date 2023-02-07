package com.example.flashlight

import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var state = false
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.flashlight.setOnClickListener {
            if (!state) {
                binding.flashlight.setImageResource(R.drawable.ic_flash_on)
                state = true
                light(state)
            } else {
                binding.flashlight.setImageResource(R.drawable.ic_flash_off)
                state = false
                light(state)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun light(state:Boolean) {
        val cameraManager : CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraId : String?

        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, state)
        } catch (e: Exception) {

        }
    }
}