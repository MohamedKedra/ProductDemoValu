package com.example.productdemovalu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productdemovalu.databinding.ItemProductLayoutBinding

class ProductAdapter : ListAdapter<String, ProductAdapter.ProductHolder>(ProductDiffCallback()) {

    inner class ProductHolder(itemProductLayoutBinding: ItemProductLayoutBinding) :
        ViewHolder(itemProductLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view =
            ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

    }


}


class ProductDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}