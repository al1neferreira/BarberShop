package com.aline.projects.barbershop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aline.projects.barbershop.databinding.ServicosItemBinding
import com.aline.projects.barbershop.model.Servicos

class ServicosAdapter(
    private val context: Context,
    private val listaServicos:MutableList<Servicos>
):RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServicosViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        holder.imgServico.setImageResource(listaServicos[position].img!!)
        holder.tvServico.text = listaServicos[position].nome
    }

    override fun getItemCount() = listaServicos.size


    inner class ServicosViewHolder(binding:ServicosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServico = binding.imgServico
        val tvServico = binding.tvServico
    }
}

