package com.arieharyana.mobilecomputing.ngopidiary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getSerializableExtra("data") as ItemModel

        val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        Glide.with(this).load(data.images?.standard_resolution?.url)
                .apply(requestOptions).into(img)

        txt_name?.text = data.tags?.get(0)
        txt_tags?.text = data.tags?.get(0)
        txt_author?.text = data.user?.full_name

        wv?.settings!!.javaScriptEnabled = true
        wv?.settings!!.javaScriptCanOpenWindowsAutomatically = true
        wv?.loadData(data.caption?.text,"text/html; charset=UTF-8", null)
    }


}