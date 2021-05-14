package com.konaly.requisicoes.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.konaly.requisicoes.R
import com.konaly.requisicoes.adapter.ProdutosAdapter
import com.konaly.requisicoes.model.Produto
import com.konaly.requisicoes.model.ProdutoItem
import com.konaly.requisicoes.network.ProdutosAPI
import com.konaly.requisicoes.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_delete_produto.view.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callServiceGetProdutos()
        swipeListener()
        irCadastro()

    }

    var c:Boolean = false


    private fun irCadastro() {
        fabCadastro.setOnClickListener {
            val intent = Intent(this,CadastroProduto::class.java)
            startActivity(intent)
        }
    }


    private fun swipeListener() {
        swipe.setOnRefreshListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            callServiceGetProdutos()
            swipe.isRefreshing = false

        }
    }

    private fun callServiceGetProdutos() {
        val produtosApi : ProdutosAPI = RetrofitInstance.getRestEngin().create(ProdutosAPI::class.java)
        val result:Call<Produto> = produtosApi.getProdutos()

        result.enqueue(object : Callback<Produto>, ProdutosAdapter.ClickProduto {
            override fun onFailure(call: Call<Produto>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Erro",Toast.LENGTH_SHORT).show()


            }

            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                val res = response.body()?.let {ProdutosAdapter(it,this) }
                response.body()
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter = res

            }

            override fun clickProduto(produto: ProdutoItem)  {
                val alert = AlertDialog.Builder(this@MainActivity)
                val inflater = layoutInflater.inflate(R.layout.activity_delete_produto,null)
                alert.setView(inflater)
                val alerta = alert.create()
                alerta.show()

                inflater.btnExcluir.setOnClickListener {
                    val produtosDApi : ProdutosAPI = RetrofitInstance.getRestEngin().create(ProdutosAPI::class.java)
                    val resultD:Call<ProdutoItem> = produtosDApi.deletarProduto(produto.id)
                    resultD.enqueue(object : Callback<ProdutoItem>{
                        override fun onFailure(call: Call<ProdutoItem>, t: Throwable) {
                            Toast.makeText(this@MainActivity,"Erro",Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<ProdutoItem>,
                            response: Response<ProdutoItem>
                        ) {

                            callServiceGetProdutos()

                            Toast.makeText(this@MainActivity,"Deletado",Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            }
        })
    }




}