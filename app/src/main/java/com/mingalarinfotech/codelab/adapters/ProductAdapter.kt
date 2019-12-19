package com.mingalarinfotech.codelab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mingalarinfotech.codelab.R
import com.mingalarinfotech.codelab.viewholders.BaseViewHolder
import com.mingalarinfotech.codelab.viewholders.ProductViewHolder

class ProductAdapter() : BaseAdapter() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_holder_product, p0, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        (p0 as ProductViewHolder).bind()
    }


    fun setData() {
        notifyDataSetChanged()
    }

}