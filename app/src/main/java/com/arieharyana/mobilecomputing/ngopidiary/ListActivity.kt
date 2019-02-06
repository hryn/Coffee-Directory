package com.arieharyana.mobilecomputing.ngopidiary

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_chat.*
import com.androidnetworking.error.ANError
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.OkHttpResponseAndStringRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import okhttp3.Response
import com.google.gson.JsonObject




class ListActivity : AppCompatActivity(){

    private val TAG = ListActivity::class.java.simpleName
    private lateinit var adapter: ItemAdapter


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_chat)


        rv_list?.layoutManager = LinearLayoutManager(this)
        rv_list?.setHasFixedSize(true)
        rv_list?.isNestedScrollingEnabled = false
        adapter = ItemAdapter(this)
        rv_list?.adapter = adapter

        pb_frame?.visibility = View.VISIBLE
        rv_list.visibility = View.GONE

        AndroidNetworking.get("https://api.instagram.com/v1/users/self/?access_token=5073343715.17332f1.f45b5d25d391455a9571186c081b1322")
                .addQueryParameter("format", "json")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsOkHttpResponseAndString(object : OkHttpResponseAndStringRequestListener {
                    override fun onResponse(okHttpResponse: Response?, response: String?) {
                        pb_frame.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE

                        Log.e("string_profile", response.toString())
                        val result = response.toString()
                        Log.e("result_profile", result)
                        val data = Gson().fromJson(response, JsonObject::class.java)
                        val check = Gson().fromJson(data.get("data"), JsonObject::class.java)

                        val requestOptions = RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .circleCrop()

                        Glide.with(applicationContext).load(check.get("profile_picture").getAsString()).apply(requestOptions).into(profile_picture)
                        name?.text = check.get("full_name").getAsString()
                        username?.text = check.get("username").getAsString()
                        bio?.text = check.get("bio").getAsString()
                        website?.text = check.get("website").getAsString()
                    }

                    override fun onError(error: ANError?) {
                        if (error?.errorCode !== 0) {
                            Log.d(TAG, "onError errorCode : " + error?.errorCode)
                            Log.d(TAG, "onError errorBody : " + error?.errorBody)
                            Log.d(TAG, "onError errorDetail : " + error?.errorDetail)
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.errorDetail)
                        }
                        pb_frame.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE
                    }

                })

        AndroidNetworking.get("https://api.instagram.com/v1/users/self/media/recent/?access_token=5073343715.17332f1.f45b5d25d391455a9571186c081b1322")
                .addQueryParameter("format", "json")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsOkHttpResponseAndString(object : OkHttpResponseAndStringRequestListener {
                    override fun onResponse(okHttpResponse: Response?, response: String?) {
                        pb_frame.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE

                        Log.e("string", response.toString())
                        val result = response.toString()
                        Log.e("result", result)
                        val check = Gson().fromJson<DataModel>(result, DataModel::class.java)
                        Log.e("check", check.toString())
                        adapter.update(check.data!!)
                    }

                    override fun onError(error: ANError?) {
                        if (error?.errorCode !== 0) {
                            Log.d(TAG, "onError errorCode : " + error?.errorCode)
                            Log.d(TAG, "onError errorBody : " + error?.errorBody)
                            Log.d(TAG, "onError errorDetail : " + error?.errorDetail)
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.errorDetail)
                        }
                        pb_frame.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE
                    }

                })


    }



}