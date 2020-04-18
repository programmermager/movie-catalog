package id.lima.moviecatalog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.CastModel

class CastAdapter(private val models: List<CastModel>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = models[position]
        holder.tvCastName?.text = data.character
        holder.tvRealName?.text = data.name
        Glide.with(context).load(Constant.imgOriginalThumbnail + data.profile_path).into(holder.ivCast)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivCast: ImageView? = itemView?.findViewById(R.id.ivCast)
        var tvCastName: TextView? = itemView?.findViewById(R.id.tvCastName)
        var tvRealName: TextView? = itemView?.findViewById(R.id.tvRealName)
    }
}