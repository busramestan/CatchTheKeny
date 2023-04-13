package com.busramestan.kotlincatchthekeny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.busramestan.kotlincatchthekeny.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    val handler = Handler()
    var runnable =Runnable{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()

        //CountDown Timer
        object : CountDownTimer(15000, 1000) {
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time : " + p0 / 1000
            }

            override fun onFinish() {

                binding.timeText.text = "Time : 0"

                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }


                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                val message = alert.setMessage("Restart The Game ?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()
    }

    fun hideImages(){

        runnable= object : Runnable{
            override fun run() {
                for (image in imageArray){
                image.visibility= View.INVISIBLE
            }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }



    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: $score"
    }

}