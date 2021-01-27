package com.example.realnavermoviefinder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieGridAdapter : BaseAdapter() {
    private val movieList = mutableListOf<ResultGetSearchMovies.Items>()//비어있는 리스트로 일단 초기화

    fun submitList(data: List<ResultGetSearchMovies.Items>) {
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()  //UI를 다시 그리는 메서드
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//      val inflater = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflater = LayoutInflater.from(parent!!.context) //요샌 이렇게 인플레이터를 받는다.
        //LayoutInflater = layout 리소스(xml)를 인스턴스화 할 때 필요한 객체 (인플레이터 통해서 리소스를 코드로 바꿈)
        val movieView: View = inflater.inflate(R.layout.item_movie_iamge, parent, false)
        //UI 표시
        val titleTextView = movieView.findViewById<TextView>(R.id.movie_title)
        val imageView =
            movieView.findViewById<ImageView>(R.id.movie_image) //액티비티 아닌 곳에서는 이런식으로 앞에 입력해주어야함.

        val data = movieList[position]
        titleTextView.text = data.title
        Glide.with(imageView).load(data.image).into(imageView)

        val result: Intent? = null
        movieView.setOnClickListener {
            //웹뷰에 해당 영화 link를 줌
            result?.getStringArrayListExtra("movie")
            // Log.d(TAG, "클릭 성공 : $binding")
        }
        return movieView
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movieList.size
    }

}

