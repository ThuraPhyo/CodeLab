package com.mingalarinfotech.codelab.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mingalarinfotech.codelab.data.CategoryVO
import kotlinx.android.synthetic.main.view_holder_product.view.*


class ProductViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private lateinit var product: CategoryVO

    var productCount: String? =
        itemView.context.getString(com.mingalarinfotech.codelab.R.string.productCount)
    init {
        itemView.setOnClickListener {

        }
    }

    fun bind(productVO: CategoryVO) {
        product = productVO
        //productCount = "${productVO.count} $productCount"
        itemView.tvLabel.text = product.label
        itemView.tvCount.text = "${productVO.count} $productCount"
        Glide.with(itemView.context)
            .load(product.icon)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.ivLogo)
    }
}