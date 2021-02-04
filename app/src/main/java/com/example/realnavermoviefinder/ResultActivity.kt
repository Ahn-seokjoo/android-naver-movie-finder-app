package com.example.realnavermoviefinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.realnavermoviefinder.databinding.ActivityResultBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val TAG = ResultActivity::class.java.simpleName
private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
private const val CLIENT_ID = "7X1yGh22vxm3pKHFpPgi"
private const val CLIENT_SECRET = "kRPqaEb4De"

//클릭 리스너 구현

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

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

        val adapter = MovieRecyclerAdapter { movie -> openWebPage(movie.link) }

        //binding.recyclerView가 반복되므로 코틀린이 제공하는 확장함수중에서 사용,
        // 예를들면 also, apply, run, let 등등
        with(binding.recyclerView) {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this@ResultActivity, 2)
            setHasFixedSize(true)
        }


        //데이터 준비
        callGetSearchMovies.enqueue(
            object : retrofit2.Callback<ResultGetSearchMovies> {
                override fun onResponse(
                    call: Call<ResultGetSearchMovies>,
                    response: Response<ResultGetSearchMovies>
                ) {
                    response.body()?.let {
                        adapter.submitList(it.items)
                    }
                    //           Log.d(TAG, "성공 : ${(response.body()!!.items)}")
                    for (element in response.body()!!.items) {
                        movieInfoList.addAll(listOf(element.title, element.link, element.image))
                    }//movieInfoList = 데이터
                    Log.d(TAG, "성공 : $movieInfoList")
                }

                override fun onFailure(call: Call<ResultGetSearchMovies>, t: Throwable) {
                    Log.d(TAG, "실패 : $t")
                }
            })
    }
}


