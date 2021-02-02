package com.example.realnavermoviefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realnavermoviefinder.MovieRecyclerAdapter.ViewHolder
import com.example.realnavermoviefinder.databinding.ItemMovieImageBinding


class MovieRecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener
    private val movieList = mutableListOf<ResultGetSearchMovies.Items>()//비어있는 리스트로 일단 초기화

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

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
            itemClickListener.onClick(it, position)
        }
    }

    fun getItem(position: Int): ResultGetSearchMovies.Items {
        return movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

//위의 if문이 필요가 없는 이유는 처음 할당 이후로 동적 할당하기에 필요가 없는게 아닐까?
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridAdapter.ViewHolder {
//        val inflater = LayoutInflater.from(parent!!.context)
//        val binding : ItemMovieImageBinding = ItemMovieImageBinding.inflate(inflater,parent,false)
//
//        return MovieGridAdapter.ViewHolder(binding)
//    }
//    class ViewHolder(val binding: ItemMovieImageBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(listener: View.OnClickListener, item: ResultGetSearchMovies) {
//            binding.movieTitle.text
//        }
//    }
//        if (convertView == null) {
////      val inflater = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val inflater = LayoutInflater.from(parent!!.context) //요샌 이렇게 인플레이터를 받는다.
//            //LayoutInflater = layout 리소스(xml)를 인스턴스화 할 때 필요한 객체 (인플레이터 통해서 리소스를 코드로 바꿈)
//            val binding: ItemMovieImageBinding =
//                ItemMovieImageBinding.inflate(inflater, parent, false) //인플레이트는 자원을 많이 사용함
//            //UI 표시          //findviwbyid도 비용이 비싸다  //뷰홀더는 이를 한번 쓴걸 이용해 재활용하겠다는 의미
//            viewHolder = MovieViewHolder(binding)
//
//            viewHolder.binding.root.tag = viewHolder //root안에 뷰가 있음
//            //tag란 모든 view에 있는 딱히 용도가 정해져 있지 않은 .. 엮고싶을 때 씀
//        } else {
//            viewHolder = convertView.tag as MovieViewHolder //컨버터. tag(뷰홀더를 꺼내오는거)
//        }
//
//    }

//    onCreateViewHolder : 화면을 최초 로딩하여 만들어진 View가 없는 경우, xml파일을 inflate하여 ViewHolder를 생성한다.
//    onBindViewHolder : 위의 onCreateViewHolder에서 만든 view와 실제 입력되는 각각의 데이터를 연결한다.
//    getItemCount : RecyclerView로 만들어지는 item의 총 개수를 반환한다.

