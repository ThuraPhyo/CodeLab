package com.mingalarinfotech.codelab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.mingalarinfotech.codelab.R
import com.mingalarinfotech.codelab.adapters.ProductAdapter
import com.mingalarinfotech.codelab.data.CategoryVO
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : BaseFragment() {
    private lateinit var mAdapter: ProductAdapter
    private var categoryList: ArrayList<CategoryVO> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_product, container, false)

        mAdapter = ProductAdapter()
        view.rvProduct.layoutManager = GridLayoutManager(activity, 2)
        view.rvProduct.adapter = mAdapter
        return view
    }

    override fun onResume() {
        db.collection("Category")
            .get()
            .addOnSuccessListener { result ->
                val categoryVOs: ArrayList<CategoryVO> = ArrayList()
                for (document in result) {
                    categoryVOs.add(document.toObject(CategoryVO::class.java))
                }
                categoryList = categoryVOs
                mAdapter.setData(categoryList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }
        super.onResume()
    }
}
