package com.example.validacaoformulariosapp.ui.validador

import android.util.Log
import com.google.android.material.textfield.TextInputLayout
import java.lang.StringBuilder

class ValidaTelefoneComDdd(val textInputLayout: TextInputLayout) : Validador {

    private val DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 e 11 digitos"
    private val validacaoPadrao = ValidacaoPadrao(textInputLayout)
    private val editText get() = textInputLayout.editText
    private val telefone get() = editText?.text.toString()


    private fun validaEntreDezOuOnzeDigitos(telefone : String): Boolean {
        val digitos = telefone.length
        if (digitos < 10 || digitos > 11) {
            textInputLayout.error = DEVE_TER_DEZ_OU_ONZE_DIGITOS
            return false
        }
        return true
    }

    override
    fun isValido(): Boolean {
        if (!validacaoPadrao.isValido()) return false
        val telefoneSemFormato = FormataTelefone().remove(telefone)
        if (!validaEntreDezOuOnzeDigitos(telefoneSemFormato)) return false
        adicionaFormatacao(telefoneSemFormato)
        return true
    }

    private fun adicionaFormatacao(telefone : String) {
        val telefoneFormatado = FormataTelefone().adiciona(telefone)
        editText?.setText(telefoneFormatado)
    }


}

