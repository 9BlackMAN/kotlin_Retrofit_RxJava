package com.ckc.kotlin_retrofit_rxjava.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency")//
    val currency : String,
    //@SerializedName("price")//eğere değişken ismi verilen veri name ile aynı ise bunu yazmaya gerek yoktur
    val price : String)
