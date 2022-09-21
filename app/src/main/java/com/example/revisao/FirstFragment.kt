package com.example.revisao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.revisao.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    val TAG = "Teste"

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnNext.setOnClickListener {
            view.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.btnEnviarMensagem.setOnClickListener {
            enviarMensagem()


        }

        return view
    }

    private fun enviarMensagem() {
            val result = binding.inputMsg.text.toString()
            // Use the Kotlin extension in the fragment-ktx artifact
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        Log.i(TAG, "Enviando result: $result")
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
