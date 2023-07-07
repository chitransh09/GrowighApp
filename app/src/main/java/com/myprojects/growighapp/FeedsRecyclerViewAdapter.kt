package com.myprojects.growighapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myprojects.growighapp.databinding.RecyclerviewSingleItemBinding

class FeedsRecyclerViewAdapter(private val items : List<PixabayImage>) : RecyclerView.Adapter<FeedsRecyclerViewAdapter.FeedsViewHolder>() {

    class FeedsViewHolder(val binding : RecyclerviewSingleItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val binding = RecyclerviewSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtHeading.text = "The Verge"
        holder.binding.txtSubHeading.text = "Tech News"
        holder.binding.txtFeedsDesc.text = "Meta explains how AI influences what we see on Facebook and Instagram"
        holder.binding.txtCommentCount.text = "View all 9 comments"
        holder.binding.txtLikes.text = "21 Likes"
        holder.binding.txtComments.text = "9 Comments"
        holder.binding.txtShare.text = "Share"
        holder.binding.btnLike.setOnClickListener {
            // Handle like button click
        }
        holder.binding.btnFav.setOnClickListener {
            // Handle favorite button click
        }

        holder.binding.contentImg.load(items) {
            crossfade(true)
            placeholder(R.drawable.dummy_image)
//            transformations(CircleCropTransformation())
        }
    }

}