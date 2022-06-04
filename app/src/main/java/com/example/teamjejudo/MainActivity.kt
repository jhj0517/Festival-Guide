package com.example.teamjejudo

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.ActivityMainBinding
import com.example.teamjejudo.retrofit.Key
import com.example.teamjejudo.retrofit.RetrofitClass
import retrofit2.Call
import retrofit2.Response
import java.net.URLDecoder

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
fun test(){
    val retrofit = RetrofitClass().api.getFestivals(URLDecoder.decode(Key,"UTF-8"),"AND","App","20220604","json")
    retrofit.enqueue(object : retrofit2.Callback<Festival>{
        override fun onResponse(call: Call<Festival>, response: Response<Festival>) {
            //할것
        }

        override fun onFailure(call: Call<Festival>, t: Throwable) {
            t.printStackTrace()
        }

    })
}