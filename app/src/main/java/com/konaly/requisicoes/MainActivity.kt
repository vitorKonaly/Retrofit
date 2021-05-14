package com.konaly.requisicoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.konaly.requisicoes.adapter.ProdutosAdapter
import com.konaly.requisicoes.model.Produto
import com.konaly.requisicoes.network.ProdutosAPI
import com.konaly.requisicoes.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callServiceGetProdutos()

    }

    private fun callServiceGetProdutos() {
        val produtosApi : ProdutosAPI = RetrofitInstance.getRestEngin().create(ProdutosAPI::class.java)
        val result:Call<Produto> = produtosApi.getProdutos()

        result.enqueue(object : Callback<Produto>{
            override fun onFailure(call: Call<Produto>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Erro",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                response.body()
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter = response.body()?.let { ProdutosAdapter(it) }
            }
        })
    }


}