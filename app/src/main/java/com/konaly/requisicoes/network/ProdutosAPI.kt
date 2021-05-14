package com.konaly.requisicoes.network

import com.konaly.requisicoes.model.Produto
import com.konaly.requisicoes.model.ProdutoItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProdutosAPI {

    @GET("produtos")
    fun getProdutos():Call<Produto>

    @FormUrlEncoded
    @POST("produtos")
    fun criarProdutos(
        @Field("nome") nome:String,
        @Field("descricao") descricao:String,
        @Field("valor_unitario") valor_unitario:String,
        @Field("path_thumb")path_thumb:String
    ):Call<ProdutoItem>

}