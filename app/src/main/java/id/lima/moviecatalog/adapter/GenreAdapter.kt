package id.lima.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.model.objects.GenreModel

class GenreAdapter(private val models: List<GenreModel>, private val listener: MenuClickListener) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = models[position]
        holder.tvItemGenre?.text = data.name
        holder.tvItemGenre?.setOnClickListener { listener.onClickMenu(position, data) }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvItemGenre: TextView? = itemView?.findViewById(R.id.tvItemGenre)
    }

    interface MenuClickListener {
        fun onClickMenu(position: Int, data: GenreModel?)
    }

}