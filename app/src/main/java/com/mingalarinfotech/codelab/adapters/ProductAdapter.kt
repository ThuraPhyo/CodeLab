package com.mingalarinfotech.codelab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mingalarinfotech.codelab.R
import com.mingalarinfotech.codelab.data.CategoryVO
import com.mingalarinfotech.codelab.viewholders.BaseViewHolder
import com.mingalarinfotech.codelab.viewholders.ProductViewHolder

class ProductAdapter() : BaseAdapter() {
    private var productLists: ArrayList<CategoryVO> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_holder_product, p0, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productLists.size
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        val product = productLists[position]
        (viewHolder as ProductViewHolder).bind(product)
    }


    fun setData(productList: ArrayList<CategoryVO>) {
        productLists = productList
        notifyDataSetChanged()
    }

}