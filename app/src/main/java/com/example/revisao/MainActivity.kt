package com.example.revisao

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.revisao.databinding.ActivityMainBinding

/**
Passo 01 : View Binding
https://developer.android.com/topic/libraries/view-binding

 Passo 02 :  Abrir outra activity e enviar uma mensagem

 Passo 03 : Receber resposta da activity Aberta
https://developer.android.com/training/basics/intents/result#custom
*/

class MainActivity : AppCompatActivity() {
    val TAG = "Teste"
    /** Passo 001  -
     * Nome do arquivo de layout comecando com letras maiusculas e tirando o _ e acrescentando
     * "Binding" no final.
     * Exemplo: activity_main -> ActivityMainBinding
     *  https://developer.android.com/topic/libraries/view-binding#setup */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        setup()
    }

    private fun setupFragmentListener(){
        supportFragmentManager
            .setFragmentResultListener("requestKey", this) { requestKey, bundle ->
                // We use a String here, but any type that can be put in a Bundle is supported
                val result = bundle.getString("bundleKey")
                // Do something with the result
                binding.tvResposta.text = result
                Log.i(TAG, "Recebendo result: $result")
            }

    }

    private fun setup() {
        setupFragmentListener()
        setupClickListeners()
    }
   /** Configura atividade de cliques*/
    private fun setupClickListeners() {
        binding.btnEnviar.setOnClickListener{
//            enviarMensagem()
            enviarMensagemParaResposta()
       }
    }
    /** Passo 002 -
     * Abre outra activity e envia a mensagem*/
    private fun enviarMensagem() {

        val mensagem = binding.inputMensagem.text.toString()
        // Parte 001   - Criacao da Intent
        val intent = Intent(this,HomeActivity::class.java)
        // Parte 002   - Passar informacao atraves da Intent
        intent.putExtra(MENSAGEM_ENVIADA, mensagem)
        intent.putExtra(IDADE_ENVIADA, 18)
        //Parte 003   - Enviar a Intent
        startActivity(intent)
    }


    /** Passo 003*/

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result  ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val numero = intent?.getIntExtra(HomeActivity.NUM_RESPOSTA, 0)
            binding.tvResposta.text = numero.toString()

            // Handle the Intent
        }
    }
    fun enviarMensagemParaResposta(){
        val mensagem = binding.inputMensagem.text.toString()
        // Parte 001   - Criacao da Intent
        val intent = Intent(this,HomeActivity::class.java)
        // Parte 002   - Passar informacao atraves da Intent
        intent.putExtra(MENSAGEM_ENVIADA, mensagem)
        intent.putExtra(IDADE_ENVIADA, 18)
        //Parte 003   - Enviar a Intent para resposta
        startForResult.launch(intent)
        /** substituido : startActivity(intent) **/
    }


    companion object{
        val MENSAGEM_ENVIADA = "Mensagem Enviada"
        val IDADE_ENVIADA = "Idade Enviada"
    }


}