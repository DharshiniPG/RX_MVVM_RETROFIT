package com.example.rx_mvvm_retrofit.viewmodal

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rx_mvvm_retrofit.adapter.MoviesAdapter
import com.example.rx_mvvm_retrofit.model.HeroClass
import com.example.rx_mvvm_retrofit.services.ApiClient
import com.example.rx_mvvm_retrofit.view.MainActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.net.URL
import java.util.*

class MainViewModal(mainActivity: MainActivity) : LifecycleObserver {
    private var movieList: List<HeroClass>? = null
    private var progressDialog: ProgressDialog? = null
    private val image: MutableList<Bitmap> = ArrayList()
    private val context: Context
    @JvmField
    var layoutManager: RecyclerView.LayoutManager
    @JvmField
    var adapter: MoviesAdapter? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        adapter = MoviesAdapter(context)
        val apiService = ApiClient.getApiService()
        val call = apiService.moviesList.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        call.subscribe(object : Observer<List<HeroClass>?> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(heroClasses: List<HeroClass>?) {
                movieList = heroClasses
                ProcessBitMaps().execute("")
            }

            override fun onError(e: Throwable) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onComplete() {}
        })
    }

    private inner class ProcessBitMaps : AsyncTask<String?, String?, Bitmap?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog!!.setMessage("Please Wait")
            progressDialog!!.isIndeterminate = false
            progressDialog!!.setCancelable(false)
            progressDialog!!.show()
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            adapter!!.setMovieData(movieList, image)
            progressDialog!!.hide()
        }

        protected override fun doInBackground(vararg strings: String): Bitmap? {
            for (i in movieList!!.indices) {
                try {
                    image.add(BitmapFactory.decodeStream(URL(movieList!![i].imageurl).content as InputStream))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return null
        }
    }

    init {
        context = mainActivity
        layoutManager = LinearLayoutManager(context)
    }
}