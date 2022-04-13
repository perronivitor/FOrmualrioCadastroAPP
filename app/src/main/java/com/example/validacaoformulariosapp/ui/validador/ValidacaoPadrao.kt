package com.example.validacaoformulariosapp.ui.validador

import com.google.android.material.textfield.TextInputLayout

class ValidacaoPadrao(val textInputLayout : TextInputLayout) : Validador{

    private val CAMPO_OBRIGATORIO = "Campo Obrigatório"

    private fun validaCampoObrigatorio(): Boolean {
        val editText = textInputLayout.editText
        val text = editText?.text.toString()
        if (text.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = CAMPO_OBRIGATORIO
            return false
        }
        return true
    }

    override
    fun isValido():Boolean{
        if (!validaCampoObrigatorio())return false
        limpaErro()
        return true
    }

    private fun limpaErro() {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

}