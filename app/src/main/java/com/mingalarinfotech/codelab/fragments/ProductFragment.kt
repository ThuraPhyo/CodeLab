package com.mingalarinfotech.codelab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mingalarinfotech.codelab.R
import com.mingalarinfotech.codelab.adapters.ProductAdapter
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : BaseFragment() {
    private lateinit var mAdapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_product, container, false)

        mAdapter = ProductAdapter()
        view.rvProduct.layoutManager = GridLayoutManager(activity, 2)
        view.rvProduct.adapter = mAdapter
        return view
    }
}
