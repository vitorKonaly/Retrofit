package com.konaly.requisicoes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.konaly.requisicoes.R
import com.konaly.requisicoes.model.Produto
import com.konaly.requisicoes.model.ProdutoItem
import kotlinx.android.synthetic.main.item.view.*

class ProdutosAdapter (var items: Produto, val click:ClickProduto):RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder =
        ProdutosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        holder.bind(items[position])
        holder.card.setOnClickListener {
            click.clickProduto(items[position])
        }
    }


    interface ClickProduto{
        fun clickProduto(produto:ProdutoItem){

        }
    }

    inner class ProdutosViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(produtoItem: ProdutoItem) {
            Glide.with(itemView).load(produtoItem.path_thumb).into(itemView.imageProduto)
            itemView.textNome.text = produtoItem.nome
            itemView.textDescricao.text = produtoItem.descricao
            itemView.textValor.text = produtoItem.valor_unitario
        }

        val card = itemView.card



    }
}