package com.konaly.requisicoes.network

import com.konaly.requisicoes.model.Produto
import retrofit2.Call
import retrofit2.http.GET

interface ProdutosAPI {

    @GET("produto")
    fun getProdutos():Call<Produto>

}