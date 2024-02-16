package com.aline.projects.barbershop.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.aline.projects.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var horario: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth <= 10) {
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10) {
                mes = "" + (monthOfYear + 1)
            } else {
                mes = (monthOfYear + 1).toString()
            }
            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->

            val minuto: String

            if (minute < 10) {
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }
            horario = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btnAgendar.setOnClickListener {
            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            when {
                horario.isEmpty() -> {
                    mensagem(it, "Escolha um horário", "#fff1d4")
                }

                horario < "8:00" && horario > "19:00" -> {
                    mensagem(it, "Insira um horário entre 8:00 e 19:00", "#fff1d4")
                }

                data.isEmpty() -> {
                    mensagem(it, "Escolha uma data", "#fff1d4")
                }

                barbeiro1.isChecked && data.isNotEmpty() && horario.isNotEmpty() -> {
                   salvarAgendamento(it, nome, "Zezinho", data, horario)
                }

                barbeiro2.isChecked && data.isNotEmpty() && horario.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Joãozinho", data, horario)
                }

                barbeiro3.isChecked && data.isNotEmpty() && horario.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Pedrinho", data, horario)
                }

                else -> {
                    mensagem(it, "Escolha um profissional", "#fff1d4")
                }

            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String) {

        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FF000000"))
        snackbar.show()
    }

    private fun salvarAgendamento(
        view: View,
        cliente: String,
        barbeiro: String,
        data: String,
        horario: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val dadosUsuario = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "horário" to horario
        )
        db.collection("agendamento")
            .document(cliente)
            .set(dadosUsuario)
            .addOnCompleteListener{
                mensagem(view,
                    "Agendamento realizado com sucesso",
                    "#fff1d4" )
            }.addOnFailureListener {
                mensagem(view, "Erro no servidor!", "#FF000000")
            }

    }
}