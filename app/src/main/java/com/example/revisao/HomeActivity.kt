package com.example.revisao

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.revisao.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        val mensagemRecebida = intent.getStringExtra(MainActivity.MENSAGEM_ENVIADA)
        binding.tvMensagemRecebida.text = mensagemRecebida

        val idadeRecebida = intent.getIntExtra(MainActivity.IDADE_ENVIADA, 0)
        binding.btnResposta.setOnClickListener {
            val numero = binding.inputResposta.text.toString().toInt()
            val intentResposta = Intent()
            intentResposta.putExtra(NUM_RESPOSTA, numero)
            setResult(Activity.RESULT_OK, intentResposta)
            finish()

        }

    }
    companion object {
        val NUM_RESPOSTA = "numeroResposta"
    }
}