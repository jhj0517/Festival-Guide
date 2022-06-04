package com.example.teamjejudo

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.room.Room
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.ActivityMainBinding
import com.example.teamjejudo.retrofit.Key
import com.example.teamjejudo.retrofit.RetrofitClass
import com.example.teamjejudo.room.LikeDB
import com.example.teamjejudo.room.LikeFestivalDB
import retrofit2.Call
import retrofit2.Response
import java.net.URLDecoder

lateinit var likeDB: LikeDB
lateinit var likeFestivalDB: LikeFestivalDB
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        likeDB = Room.databaseBuilder(applicationContext, LikeDB::class.java,"likeDB").build()
        likeFestivalDB = Room.databaseBuilder(applicationContext, LikeFestivalDB::class.java, "likePlaceDB").build()
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
