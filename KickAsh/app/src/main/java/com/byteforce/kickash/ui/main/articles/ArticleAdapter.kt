package com.byteforce.kickash.ui.main.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byteforce.kickash.databinding.ItemArticlesBinding
import com.squareup.picasso.Picasso

class ArticleAdapter(var list: List<ArticleModel>, var onClickListener:(ArticleModel)->Unit): RecyclerView.Adapter<ArticleAdapter.VH>() {


    class VH(private var view: ItemArticlesBinding, var onClickListener:(ArticleModel)->Unit) : RecyclerView.ViewHolder(view.root) {


        fun bind(model: ArticleModel) {

            Picasso.get().load(model.imageUrl).into(view.ivArticleImage)
            view.tvArticleTitle.text = model.title
            view.tvPostedBy.text = model.postedBy
            view.tvPostedOn.text = "Posted: "+model.datePosted

            view.llRoot.setOnClickListener {
                onClickListener(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemArticlesBinding.inflate(LayoutInflater.from(parent.context),parent,false), onClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(list[position])
    }

}