package com.example.realnavermoviefinder.view.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realnavermoviefinder.databinding.ItemMovieImageBinding
import com.example.realnavermoviefinder.repository.ResultGetSearchMovies
import com.example.realnavermoviefinder.view.result.MovieRecyclerAdapter.ViewHolder


class MovieRecyclerAdapter(val itemClickListener: (movie: ResultGetSearchMovies.Items) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    //SAM = Single Abstract Method 방식
//    interface ItemClickListener {
//        fun onClick(view: View, position: Int)
//    }
    // ==> 함수형태로 바꿀 수 있다.
    // (view: View, position:Int) -> Unit

    private val movieList = mutableListOf<ResultGetSearchMovies.Items>()//비어있는 리스트로 일단 초기화

    fun submitList(data: List<ResultGetSearchMovies.Items>) {
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()  //UI를 다시 그리는 메서드
    }

    //뷰홀더를 통해 뷰를 얻어 초기화하는데, 뷰바인딩을 통해 그 길이를 크게 줄임
    class ViewHolder(val binding: ItemMovieImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultGetSearchMovies.Items) {
            binding.movieTitle.text = result.title
            Glide.with(binding.movieImage).load(result.image).into(binding.movieImage)
        }
    }

    //여기서 getView 처럼 인플레이터를 통해 연결?해야할듯하다 리턴값은 뷰홀더로
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieImageBinding = ItemMovieImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(movieList[position]) //invoke 함수 실행
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

//    onCreateViewHolder : 화면을 최초 로딩하여 만들어진 View가 없는 경우, xml파일을 inflate하여 ViewHolder를 생성한다.
//    onBindViewHolder : 위의 onCreateViewHolder에서 만든 view와 실제 입력되는 각각의 데이터를 연결한다.
//    getItemCount : RecyclerView로 만들어지는 item의 총 개수를 반환한다.

