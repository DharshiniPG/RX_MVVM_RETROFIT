package com.example.rx_mvvm_retrofit.viewmodal;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rx_mvvm_retrofit.adapter.MoviesAdapter;
import com.example.rx_mvvm_retrofit.model.HeroClass;
import com.example.rx_mvvm_retrofit.services.ApiClient;
import com.example.rx_mvvm_retrofit.services.ApiService;
import com.example.rx_mvvm_retrofit.view.MainActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModal implements LifecycleObserver {
    private List<HeroClass> movieList;
    private ProgressDialog progressDialog;
    private List<Bitmap> image = new ArrayList<>();
    private Context context;
    public RecyclerView.LayoutManager layoutManager;
    public MoviesAdapter adapter;


    public MainViewModal(MainActivity mainActivity) {
        this.context = mainActivity;
        layoutManager = new LinearLayoutManager(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void init(){
        adapter = new MoviesAdapter(context);

        ApiService apiService = ApiClient.getApiService();
        Observable<List<HeroClass>> call = apiService.getMoviesList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());


        call.subscribe(new Observer<List<HeroClass>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<HeroClass> heroClasses) {
                movieList = heroClasses;
                new ProcessBitMaps().execute("");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    private class ProcessBitMaps extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            adapter.setMovieData(movieList, image);
            progressDialog.hide();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            for (int i = 0; i < movieList.size(); i++){
                try{
                    image.add(BitmapFactory.decodeStream((InputStream) new URL(movieList.get(i).getImageurl()).getContent()));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
