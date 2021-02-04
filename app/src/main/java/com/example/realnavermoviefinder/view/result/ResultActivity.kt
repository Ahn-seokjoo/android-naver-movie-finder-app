package com.example.realnavermoviefinder.view.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.realnavermoviefinder.databinding.ActivityResultBinding
import com.example.realnavermoviefinder.repository.MovieRepository

val TAG = ResultActivity::class.java.simpleName

//클릭 리스너 구현

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val movieRepository = MovieRepository()

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

        val adapter = MovieRecyclerAdapter { movie -> openWebPage(movie.link) }
        movieRepository.getMovies(movieTitle) {
            adapter.submitList(it)
        }
        //binding.recyclerView가 반복되므로 코틀린이 제공하는 확장함수중에서 사용,
        // 예를들면 also, apply, run, let 등등
        with(binding.recyclerView) {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this@ResultActivity, 2)
            setHasFixedSize(true)
        }

    }
}


