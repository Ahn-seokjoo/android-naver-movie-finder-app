package com.example.realnavermoviefinder

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.realnavermoviefinder.databinding.ItemMovieIamgeBinding

class GridAdapter(private var context: Context?, private var movieList: ResultGetSearchMovies): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View  {

       // val binding = ItemMovieIamgeBinding.inflate(LayoutInflater.from(context))
        //val movie : ResultGetSearchMovies = movieList
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val movieView : View = inflater.inflate(R.layout.item_movie_iamge,null)
        val result : Intent ?= null

        movieView.setOnClickListener{
            //웹뷰에 해당 영화 link를 줌
            result?.getStringArrayListExtra("movie")
           // Log.d(TAG, "클릭 성공 : $binding")
        }
        return movieView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movieList.items.size
    }

}

