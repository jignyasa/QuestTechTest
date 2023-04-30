package com.example.questtechtest

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.questtechtest.adapter.PostListAdapter
import com.example.questtechtest.model.PostList
import com.example.questtechtest.remote.RetrofitClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostListActivity : AppCompatActivity(), PostListAdapter.onItemClickListener {
    private lateinit var adapter: PostListAdapter
    private lateinit var rvPost: RecyclerView
    private lateinit var btnSubmit: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPost = findViewById(R.id.rvPostRecyclerview)
        btnSubmit = findViewById(R.id.btnSubmit)
        getPostData()
        initView()
    }

    private fun initView() {
        adapter = PostListAdapter(this)
        rvPost.adapter = adapter
        rvPost.layoutManager = LinearLayoutManager(this)
        btnSubmit.setOnClickListener {
            var selectedlist = ""
            var list = adapter.getSelectedPostList()
            Log.e("selected", list.toString())
            for (i in list) {
                selectedlist.plus("\t,\t")
                selectedlist += "\t,\t ${i.id}"
            }
            var alertDialog = AlertDialog.Builder(this)
            var alert: AlertDialog = alertDialog.create()
            alertDialog.setPositiveButton("ok", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    alert.cancel()
                }
            })
            alert.setTitle("Selected Ids")
            alert.setMessage(selectedlist)
            alert.show()
        }
    }

    private fun getPostData() {
        RetrofitClient.getInstance()?.getListData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<ArrayList<PostList>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: ArrayList<PostList>) {
                    Log.e("jigs data", t.get(0).title.toString())
                    adapter.addData(t)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    override fun onItemClick(data: PostList) {
        val intent = Intent(this@PostListActivity, PostDetailActivity::class.java).putExtra("DATA", data)
        startActivity(intent)
    }
}