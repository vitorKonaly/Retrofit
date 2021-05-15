package com.konaly.requisicoes.network

import com.konaly.requisicoes.model.Produto
import com.konaly.requisicoes.model.ProdutoItem
import retrofit2.Call
import retrofit2.http.*

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
    @FormUrlEncoded
    @PUT("produtos/{id}")
    fun editProduto(
        @Path("id")id:Int,
        @Field("nome") nome:String,
        @Field("descricao") descricao:String,
        @Field("valor_unitario") valor_unitario:String,
        @Field("path_thumb")path_thumb:String
    ):Call<ProdutoItem>

    @DELETE("produtos/{id}")
    fun deletarProduto(
    @Path("id")id:Int
    ):Call<ProdutoItem>
}