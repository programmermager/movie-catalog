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
import id.lima.moviecatalog.data.model.objects.ReviewModel

class ReviewAdapter(private val models: List<ReviewModel>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = models[position]
        holder.tvName?.text = data.author
        holder.tvReview?.text = data.content
    }

    override fun getItemCount(): Int {
        return models.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvReview: TextView? = itemView?.findViewById(R.id.tvReview)
        var tvName: TextView? = itemView?.findViewById(R.id.tvName)
    }
}