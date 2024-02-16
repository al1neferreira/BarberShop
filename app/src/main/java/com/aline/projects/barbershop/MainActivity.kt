package com.aline.projects.barbershop


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aline.projects.barbershop.databinding.ActivityMainBinding
import com.aline.projects.barbershop.view.Home
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when {
                nome.isEmpty() -> {
                    mensagem(it, "Digite o seu nome")
                }

                senha.isEmpty() -> {
                    mensagem(it, "Digite a sua senha")
                }

                senha.length <= 5 -> {
                    mensagem(it, "A senha deve ter pelo menos 6 caracteres")
                }

                else -> {
                    navegarParaHome(nome)
                }
            }

        }
    }

    private fun mensagem(view: View, mensagem: String) {

        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#fff1d4"))
        snackbar.setTextColor(Color.parseColor("#FF000000"))
        snackbar.show()

    }

    private fun navegarParaHome(nome: String) {
        val intent = Intent(this, Home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

}
