package com.example.questtechtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.example.questtechtest.model.PostList
import com.example.questtechtest.remote.RetrofitClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostDetailActivity : AppCompatActivity() {
    private lateinit var tvDetailsText:AppCompatTextView
    lateinit var data:PostList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        tvDetailsText=findViewById(R.id.tvDetails)
        if(intent!=null){
            data=intent.getSerializableExtra("DATA") as PostList
            getPostData()
        }
    }


    private fun getPostData() {
        RetrofitClient.getInstance()?.getPurticularPost(data.id?:0)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<PostList> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: PostList) {
                    Log.e("jigs data", t.toString())
                    tvDetailsText.text=t.userId.toString().plus("\n").plus(t.title.plus("\n").plus(t.body))
                }

                override fun onError(e: Throwable) {

                }

            })
    }

}