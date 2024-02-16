package com.aline.projects.barbershop.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.aline.projects.barbershop.R
import com.aline.projects.barbershop.adapter.ServicosAdapter
import com.aline.projects.barbershop.databinding.ActivityHomeBinding
import com.aline.projects.barbershop.databinding.ActivityMainBinding
import com.aline.projects.barbershop.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val nome = intent.extras?.getString("nome")
        binding.tvNomeUsuario.text = "Bem-vindo(a), $nome"

        val recyclerViewServicos = binding.recyclerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this, 2)

        servicosAdapter = ServicosAdapter(this, listaServicos)
        recyclerViewServicos.setHasFixedSize(true)

        recyclerViewServicos.adapter = servicosAdapter

        getServicos()

        binding.btnAgendamento.setOnClickListener {
            val intent = Intent(this, Agendamento::class.java)

            intent.putExtra("nome", nome)

            startActivity(intent)
        }
    }

    fun getServicos() {

        val servico1 = Servicos(R.drawable.img1, "Corte de cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.img2, "Corte de barba")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.img3, "Lavagem de cabelo")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.img4, "Tratamento capilar")
        listaServicos.add(servico4)

    }
}