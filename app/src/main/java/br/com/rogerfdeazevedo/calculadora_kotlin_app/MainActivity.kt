package br.com.rogerfdeazevedo.calculadora_kotlin_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        btn_num_0.setOnClickListener{ setExpressao("0", true)}
        btn_num_1.setOnClickListener{ setExpressao("1", true)}
        btn_num_2.setOnClickListener{ setExpressao("2", true)}
        btn_num_3.setOnClickListener{ setExpressao("3", true)}
        btn_num_4.setOnClickListener{ setExpressao("4", true)}
        btn_num_5.setOnClickListener{ setExpressao("5", true)}
        btn_num_6.setOnClickListener{ setExpressao("6", true)}
        btn_num_7.setOnClickListener{ setExpressao("7", true)}
        btn_num_8.setOnClickListener{ setExpressao("8", true)}
        btn_num_9.setOnClickListener{ setExpressao("9", true)}

        btn_ponto.setOnClickListener{ setExpressao(".", true)}

        btn_operador_adicao.setOnClickListener{ setExpressao("+", false)}
        btn_operador_subtracao.setOnClickListener{ setExpressao("-", false)}
        btn_operador_multiplicacao.setOnClickListener{ setExpressao("x", false)}
        btn_operador_divisao.setOnClickListener{ setExpressao("/", false)}

        btn_limpar.setOnClickListener {
            txtView_expressao.text = ""
            txtView_resultado.text = ""
        }

        btn_backspace.setOnClickListener {
            val str = txtView_expressao.text.toString()

            if(str.isNotBlank()){
                txtView_expressao.text = str.substring(0, str.length - 1)
            }

            txtView_resultado.text = ""

        }

        btn_operador_igual.setOnClickListener{

            try {

                val expressao = ExpressionBuilder(txtView_expressao.text.toString().replace("x","*")).build()
                val resultado = expressao.evaluate()
                val longResultado = resultado.toLong()

                if(resultado == longResultado.toDouble()){
                    txtView_resultado.text = longResultado.toString()
                } else {
                    txtView_resultado.text = resultado.toString()
                }

            } catch (e: Exception) {
                print(e.message)
            }

        }

    }

    fun setExpressao(numero: String, limparExpressao: Boolean) {

        if(txtView_resultado.text.isNotEmpty()){
            txtView_expressao.text = ""
        }

        if(limparExpressao){
            txtView_resultado.text = ""
            txtView_expressao.append(numero)
        } else {
            txtView_expressao.append(txtView_resultado.text)
            txtView_expressao.append(numero)
            txtView_resultado.text = ""
        }

    }

}