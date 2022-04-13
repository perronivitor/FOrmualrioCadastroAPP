package com.example.validacaoformulariosapp.ui.validador

import android.util.Log
import com.google.android.material.textfield.TextInputLayout
import java.lang.StringBuilder

class ValidaTelefoneComDdd(val textInputLayout: TextInputLayout) {

    private val DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 e 11 digitos"
    private val validacaoPadrao = ValidacaoPadrao(textInputLayout)
    private val editText get() = textInputLayout.editText
    private val telefone get() = editText?.text.toString()


    private fun validaEntreDezOuOnzeDigitos(): Boolean {
        val digitos = telefone.length
        if (digitos < 10 || digitos > 11) {
            textInputLayout.error = DEVE_TER_DEZ_OU_ONZE_DIGITOS
            return false
        }
        return true
    }

    fun isValido(): Boolean {
        if (!validacaoPadrao.isValido()) return false
        if (!validaEntreDezOuOnzeDigitos()) return false
        adicionaFormatacao()
        return true
    }

    private fun adicionaFormatacao() {
        val telefoneFormatado = FormataTelefone().adiciona(telefone)
        editText?.setText(telefoneFormatado)
    }


}

