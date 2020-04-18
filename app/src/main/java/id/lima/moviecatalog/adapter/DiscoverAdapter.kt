package id.lima.moviecatalog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.daimajia.easing.linear.Linear
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.GenreModel
import id.lima.moviecatalog.data.model.objects.MovieModel

class DiscoverAdapter(private val models: List<MovieModel>, private val listener: MenuClickListener) : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    var context : Context ? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = models[position]
        holder.tvTitleMovie?.text = data.title
        Glide.with(context).load(Constant.imgOriginalThumbnail+data.poster_path).into(holder.ivMovie)
        holder.container?.setOnClickListener { listener.onClickMenu(position, data) }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivMovie: ImageView? = itemView?.findViewById(R.id.ivMovie)
        var tvTitleMovie: TextView? = itemView?.findViewById(R.id.tvTitleMovie)
        var container: LinearLayout? = itemView?.findViewById(R.id.container)
    }

    interface MenuClickListener {
        fun onClickMenu(position: Int, data: MovieModel?)
    }

}