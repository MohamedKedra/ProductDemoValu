package com.example.productdemovalu.view.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.productdemovalu.databinding.ItemProductLayoutBinding
import com.example.productdemovalu.remote.Product

class ProductAdapter(private val context: Context) :
    ListAdapter<Product, ProductAdapter.ProductHolder>(ProductDiffCallback()) {

    inner class ProductHolder(private val itemProductLayoutBinding: ItemProductLayoutBinding) :
        ViewHolder(itemProductLayoutBinding.root) {
        fun bind(product: Product) {
            with(itemProductLayoutBinding) {
                product.apply {
                    tvTitle.text = title
                    tvPrice.text = price.toString()
                    Glide.with(context).load(image).into(ivPic)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view =
            ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(getItem(position))
    }


}


class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}