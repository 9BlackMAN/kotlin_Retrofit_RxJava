package com.ckc.kotlin_retrofit_rxjava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ckc.kotlin_retrofit_rxjava.R
import com.ckc.kotlin_retrofit_rxjava.model.CryptoModel
import com.ckc.kotlin_retrofit_rxjava.service.CryptoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var compositeDisposable : CompositeDisposable? = null
    private var job : Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retroFit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
           // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoApi::class.java)


        compositeDisposable = CompositeDisposable()

       /* compositeDisposable?.add(retroFit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::baslatici)
            )

        */

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retroFit.getData()

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        for (data : CryptoModel in cryptoModels!!){
                            println(data.price)
                        }
                    }
                }
            }
        }





        /*
        val service = retroFit.getData()

        service.enqueue(object :Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>?,
                response: Response<List<CryptoModel>>?
            ) {

                if (response!!.isSuccessful) {
                    response?.body().let {

                        cryptoModels = ArrayList(it)

                        for (cryptoModel : CryptoModel in cryptoModels!!) {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>?, t: Throwable?) {
            }

        })


         */

    }
    fun baslatici(data  : List<CryptoModel>){
        cryptoModels = ArrayList(data)
        for (cryptoModel : CryptoModel in cryptoModels!!) {
            println(cryptoModel.currency)
            println(cryptoModel.price)
        }
    }


}