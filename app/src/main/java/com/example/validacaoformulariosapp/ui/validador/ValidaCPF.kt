package com.example.validacaoformulariosapp.ui.validador

import android.util.Log
import br.com.caelum.stella.format.CPFFormatter
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import com.google.android.material.textfield.TextInputLayout

class ValidaCPF(val textInputLayout: TextInputLayout) : Validador {

    private val ERRO_ONZE_DIGITOS = "CPF precisa ter 11 dígitos"
    private val ERRO_FORMATO_INVALIDO= "Formato CPF inválido"

    private val cpf get() = textInputLayout.editText?.text.toString()
    private val editText get() = textInputLayout.editText
    private val validadorPadrao = ValidacaoPadrao(textInputLayout)
    private val cpfFormatter = CPFFormatter()

    private fun validaCalculoCPF(cpf : String): Boolean {
        try {
            val validator = CPFValidator()
            validator.assertValid(cpf)
        } catch (e: InvalidStateException) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = ERRO_FORMATO_INVALIDO
            return false
        }
        return true
    }


    private fun validaCampoComOnzeDigitos(cpf : String): Boolean {
        if (cpf.length != 11) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = ERRO_ONZE_DIGITOS
            return false
        }
        return true
    }

    override
    fun isValido() : Boolean{
        if (!validadorPadrao.isValido()) return false
        var cpfSemFormato = cpf
        try {
            cpfSemFormato = cpfFormatter.unformat(cpf)
        } catch (e: IllegalArgumentException) {
            Log.e("FormularioCadastroAc", "configuraCampoCPF: ${e.message} ")
        }
        if (!validaCampoComOnzeDigitos(cpfSemFormato)) return false
        if (!validaCalculoCPF(cpfSemFormato)) return false
        adicionaFormatacao(cpfSemFormato)
        return true
    }

    private fun adicionaFormatacao(cpf: String ){
        val cpfFormatado = cpfFormatter.format(cpf).toString()
        editText?.setText(cpfFormatado)
    }

}