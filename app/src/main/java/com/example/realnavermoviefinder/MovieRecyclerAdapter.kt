package com.example.realnavermoviefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realnavermoviefinder.MovieRecyclerAdapter.ViewHolder


class MovieRecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val movieList = mutableListOf<ResultGetSearchMovies.Items>()//비어있는 리스트로 일단 초기화
    fun submitList(data: List<ResultGetSearchMovies.Items>) {
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()  //UI를 다시 그리는 메서드
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        private val movieImage = view.findViewById<ImageView>(R.id.movie_image)

        fun bind(result: ResultGetSearchMovies.Items) {
            movieTitle.text = result.title
            Glide.with(movieImage).load(result.image).into(movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_image, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

//    class ViewHolder(val binding: ItemMovieImageBinding) : RecyclerView.ViewHolder(binding.root){
//        fun bind(item:ResultGetSearchMovies.Items){
//            binding.movieTitle.text = item.title
//            Glide.with(binding.movieImage).load(item.image).into(binding.movieImage)
//        }
//    }
//    //본래 뷰홀더란 뷰를 얻어서 초기화 함이 목적이다. 이처럼 뷰바인딩 통해 안의 내용을 정리해버림
//
//
//    //여기서 getView 처럼 인플레이터를 통해 연결?해야할듯하다 리턴값은 뷰홀더로
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding: ItemMovieImageBinding = ItemMovieImageBinding.inflate(inflater,parent,false)
//
//        return ViewHolder(binding)
//    }
//    var mPosition = 0
//    fun setPosition(position: Int){
//        mPosition = position
//    }
//
//    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
//        holder.bind(movieList[position])
//        holder.itemView.setOnClickListener{
//            view -> setPosition(position)
//            Toast.makeText(view.context, "$position 아이템 클릭 ", Toast.LENGTH_SHORT).show()
//
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return movieList.size
//    }

//위의 if문이 필요가 없는 이유는 처음 할당 이후로 동적 할당하기에 필요가 없는게 아닐까?
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridAdapter.ViewHolder {
//        val inflater = LayoutInflater.from(parent!!.context)
//        val binding : ItemMovieImageBinding = ItemMovieImageBinding.inflate(inflater,parent,false)
//
//        return MovieGridAdapter.ViewHolder(binding)
//    }
//
//    class ViewHolder(val binding: ItemMovieImageBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(listener: View.OnClickListener, item: ResultGetSearchMovies) {
//            binding.movieTitle.text
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = movieList[position]
//
//    }
//
//    override fun getItemCount(): Int {
//        return movieList.size
//    }


//            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val viewHolder: MovieViewHolder
//        val inflater = LayoutInflater.from(parent!!.context)
//        val binding: ItemMovieImageBinding = ItemMovieImageBinding.inflate(inflater,parent,false)
//        viewHolder = MovieViewHolder(binding)
//        viewHolder.binding.root.tag = viewHolder
//
//        val data = movieList[position]
//        viewHolder.binding.movieTitle.text = data.title
//        Glide.with(viewHolder.binding.movieImage).load(data.image)
//        .into(viewHolder.binding.movieImage)

//        val viewHolder: MovieViewHolder

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

