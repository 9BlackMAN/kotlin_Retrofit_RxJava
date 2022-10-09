package com.ckc.kotlin_retrofit_rxjava.service

import com.ckc.kotlin_retrofit_rxjava.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface CryptoApi {


     @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
     fun getData() : Observable<List<CryptoModel>>


     // fun getData() : Call<List<CryptoModel>>

}