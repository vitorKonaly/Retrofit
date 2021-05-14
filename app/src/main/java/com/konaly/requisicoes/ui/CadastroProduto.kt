package com.konaly.requisicoes.ui

import android.icu.util.Currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.konaly.requisicoes.R
import com.konaly.requisicoes.model.ProdutoItem
import com.konaly.requisicoes.network.ProdutosAPI
import com.konaly.requisicoes.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_cadastro_produto.*
import kotlinx.android.synthetic.main.activity_main.*
import me.abhinay.input.CurrencyEditText
import me.abhinay.input.CurrencySymbols.USA
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CadastroProduto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        cadastrar()

    }

    private fun cadastrar() {
        buttonCadastrar.setOnClickListener {

            val produtosAPI:ProdutosAPI = RetrofitInstance.getRestEngin().create(ProdutosAPI::class.java)
            val result:Call<ProdutoItem> = produtosAPI.criarProdutos(
                nomeProduto.text.toString(),descProduto.text.toString(),valorProduto.text.toString(),"https://bslacademiadelideres.com.br/wp-content/uploads/2020/07/placeholder.png"
            )

            result.enqueue(object : Callback<ProdutoItem>{
                override fun onFailure(call: Call<ProdutoItem>, t: Throwable) {
                    Toast.makeText(this@CadastroProduto,"ERRO",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ProdutoItem>, response: Response<ProdutoItem>) {
                    Toast.makeText(this@CadastroProduto,"CADASTRADO",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}