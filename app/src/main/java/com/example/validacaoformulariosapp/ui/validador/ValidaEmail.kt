package com.example.validacaoformulariosapp.ui.validador

import com.google.android.material.textfield.TextInputLayout

class ValidaEmail(val textInputLayout: TextInputLayout) {

    private val EMAIL_INVALIDO ="E-mail inválido"
    private val editText get() = textInputLayout.editText
    private val email get() = editText?.text.toString()
    private val validadorPadrao = ValidacaoPadrao(textInputLayout)


    private fun validaPadrao():Boolean{
        if(email.matches(regex = ".+@.+\\..+".toRegex())) return true
        textInputLayout.error = EMAIL_INVALIDO
        return false
    }

    fun isValido(): Boolean{
        if(!validadorPadrao.isValido()) return false
        if(!validaPadrao()) return false
        return true
    }


}
