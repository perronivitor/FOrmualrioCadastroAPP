package com.example.validacaoformulariosapp.ui.validador

import br.com.caelum.stella.format.CPFFormatter
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import com.google.android.material.textfield.TextInputLayout

class ValidaCPF(val textInputLayout: TextInputLayout) {

    private val ERRO_ONZE_DIGITOS = "CPF precisa ter 11 dígitos"
    private val ERRO_FORMATO_INVALIDO= "Formato CPF inválido"

    private val cpf get() = textInputLayout.editText?.text.toString()
    private val editText get() = textInputLayout.editText
    private val validadorPadrao = ValidacaoPadrao(textInputLayout)

    private fun validaCalculoCPF(): Boolean {
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


    private fun validaCampoComOnzeDigitos(): Boolean {

        if (cpf.length != 11) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = ERRO_ONZE_DIGITOS
            return false
        }
        return true

    }

    fun isValido() : Boolean{
        if (!validadorPadrao.isValido()) return false
        if (!validaCampoComOnzeDigitos()) return false
        if (!validaCalculoCPF()) return false
        adicionaFormatacao()
        return true
    }

    private fun adicionaFormatacao() {
        val cpfFormatter = CPFFormatter()
        val cpfFormatado = cpfFormatter.format(cpf).toString()
        editText?.setText(cpfFormatado)
    }

}