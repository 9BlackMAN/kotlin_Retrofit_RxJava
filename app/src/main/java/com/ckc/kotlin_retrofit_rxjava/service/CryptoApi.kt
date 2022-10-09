package com.ckc.kotlin_retrofit_rxjava.service

import com.ckc.kotlin_retrofit_rxjava.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface CryptoApi {


     @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
     suspend fun getData(): Response<List<CryptoModel>>



     //fun getData() : Observable<List<CryptoModel>> //RxJava


     // fun getData() : Call<List<CryptoModel>>  //retrofit


}