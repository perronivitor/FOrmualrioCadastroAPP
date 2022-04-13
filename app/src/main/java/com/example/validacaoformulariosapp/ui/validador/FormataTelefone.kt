package com.example.validacaoformulariosapp.ui.validador

import android.widget.EditText

class FormataTelefone {

    fun adiciona(telefone : String) =
        telefone.replace("([0-9]{2})([0-9]{4})([0-9]{4,5})".toRegex(),"($1) $2-$3")

    fun remove(telefone: String) : String{
        return  telefone
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")

    }
}