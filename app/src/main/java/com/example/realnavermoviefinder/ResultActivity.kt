package com.example.realnavermoviefinder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.realnavermoviefinder.databinding.ActivityMainBinding
import com.example.realnavermoviefinder.databinding.ActivityMainBinding.inflate
import com.example.realnavermoviefinder.databinding.ActivityResultBinding
import com.example.realnavermoviefinder.databinding.ActivityResultBinding.inflate
import com.example.realnavermoviefinder.databinding.ItemMovieIamgeBinding
import com.example.realnavermoviefinder.databinding.ItemMovieIamgeBinding.inflate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Math.E
import java.lang.invoke.MethodHandleInfo.toString
import java.util.EnumSet.range
import java.util.Objects.toString
import java.util.concurrent.TimeUnit
import kotlin.reflect.typeOf


val TAG = ResultActivity::class.java.simpleName
private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
private const val CLIENT_ID = "7X1yGh22vxm3pKHFpPgi"
private const val CLIENT_SECRET = "kRPqaEb4De"

class ResultActivity : AppCompatActivity() {
private lateinit var binding: ActivityResultBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    binding = ActivityResultBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val movieTitle = intent.getStringExtra("movieTitle").toString()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_NAVER_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(NaverAPI::class.java)
    val callGetSearchMovies = api.getSearchMovies(CLIENT_ID, CLIENT_SECRET, movieTitle)
    val movieInfoList = ArrayList<String>()

    //데이터 준비
    callGetSearchMovies.enqueue(object : retrofit2.Callback<ResultGetSearchMovies> {
        override fun onResponse(
            call: Call<ResultGetSearchMovies>,
            response: Response<ResultGetSearchMovies>){
    //           Log.d(TAG, "성공 : ${(response.body()!!.items)}")
            for(element in response.body()!!.items) {
                movieInfoList.addAll(listOf(element.title,element.link,element.image))
            }//movieInfoList = 데이터
            Log.d(TAG,"성공 : $movieInfoList")
          //  val nextIntent = Intent(this@ResultActivity,ResultActivity::class.java)
           // nextIntent.getStringArrayListExtra(movieInfoList.toString())
//            startActivity(nextIntent)

          //  val adapter = GridAdapter(this@ResultActivity, response.body()!!)
          //  setContentView(binding.root)
           // startActivity(nextIntent)
        }
        override fun onFailure(call: Call<ResultGetSearchMovies>, t: Throwable) {
            Log.d(TAG, "실패 : $t")
        }
    })
    val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,movieInfoList)
    binding.gridView.adapter = adapter


}

}

