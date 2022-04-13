package com.example.validacaoformulariosapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import br.com.caelum.stella.format.CPFFormatter
import com.example.validacaoformulariosapp.R
import com.example.validacaoformulariosapp.ui.validador.FormataTelefone
import com.example.validacaoformulariosapp.ui.validador.ValidaCPF
import com.example.validacaoformulariosapp.ui.validador.ValidaTelefoneComDdd
import com.example.validacaoformulariosapp.ui.validador.ValidacaoPadrao
import com.example.validacaoformulariosapp.ui.validador.ValidaEmail
import com.google.android.material.textfield.TextInputLayout

class FormularioCadastroActivity : AppCompatActivity(R.layout.activity_formulario_cadastro) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraNomeCompleto()
        configuraCampoCPF()
        configuraCampoTelefone()
        configuraCampoEmail()
        configuraCampoSenha()

    }

    private fun configuraCampoSenha() {

        val textInputSenha = findViewById<TextInputLayout>(R.id.formulario_cadastro_campo_senha)
        adicionaValidacaoPadrao(textInputSenha)

    }

    private fun configuraCampoEmail() {
        val textInputEmail = findViewById<TextInputLayout>(R.id.formulario_cadastro_campo_email)
        val editTextEmail = textInputEmail?.editText
        val validador = ValidaEmail(textInputEmail)
        editTextEmail?.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                validador.isValido()
            }
        }
    }

    private fun configuraCampoTelefone() {

        val textInputTelefone =
            findViewById<TextInputLayout>(R.id.formulario_cadastro_campo_telefone)
        val editTelefone = textInputTelefone.editText
        val validadorTelefone = ValidaTelefoneComDdd(textInputTelefone)
        val formata = FormataTelefone()

        editTelefone?.setOnFocusChangeListener { view, hasFocus ->
            val telefone = editTelefone.text.toString()
            if (hasFocus) {
                editTelefone.setText(formata.remove(telefone))
            } else {
                validadorTelefone.isValido()
            }
        }
    }

    private fun configuraCampoCPF() {

        val textInputCPF = findViewById<TextInputLayout>(R.id.formulario_cadastro_campo_cpf)
        val editText = textInputCPF.editText
        val validador = ValidaCPF(textInputCPF)

        editText?.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                removeFormatacaoCpf(editText)
            } else {
                !validador.isValido()
            }
        }

    }

    private fun removeFormatacaoCpf(editText: EditText) {
        try {
            val cpfFormatter = CPFFormatter()
            val cpf = editText.text.toString()
            val cpfSemFormato = cpfFormatter.unformat(cpf)
            editText.setText(cpfSemFormato)
        } catch (e: IllegalArgumentException) {
            Log.e("FormularioCadastroAc", "configuraCampoCPF: ${e.message} ")
        }
    }


    private fun configuraNomeCompleto() {

        val textInputNome = findViewById<TextInputLayout>(R.id.formulario_cadastro_campo_nome)
        adicionaValidacaoPadrao(textInputNome)

    }

    private fun adicionaValidacaoPadrao(textInputLayout: TextInputLayout) {

        val validador = ValidacaoPadrao(textInputLayout)
        textInputLayout.editText?.setOnFocusChangeListener { view, hasFocus ->
            if (!validador.isValido()) return@setOnFocusChangeListener
        }

    }

}