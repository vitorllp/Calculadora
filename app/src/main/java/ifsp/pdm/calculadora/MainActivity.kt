package ifsp.pdm.calculadora

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import ifsp.pdm.calculadora.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private var pontoInserido1: Boolean = false
    private var pontoInserido2: Boolean = false
    private var operador: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.modoAvancado.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.modoNormalMI){
            activityMainBinding.modoAvancado.visibility = View.GONE
        } else {
            activityMainBinding.modoAvancado.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }

    fun numeroCalculadora(view: View) {
        val numero: Button = view as Button
        var numbers1 = activityMainBinding.operando1.text as String
        var numbers2 = activityMainBinding.operando2.text as String

        if(!operador && activityMainBinding.operando1.text.length < 6){
            numbers1 += numero.text as String
            activityMainBinding.operando1.text = numbers1
        }
        if(operador && activityMainBinding.operando2.text.length < 6 && activityMainBinding.operador.text != "sqrt"){
            numbers2 += numero.text as String
            activityMainBinding.operando2.text = numbers2
        }
    }

    fun zerarAtributos(){
        this.pontoInserido1 = false
        this.pontoInserido2 = false
        this.operador = false
    }

    fun limparInputMain(view: View){
        zerarAtributos()
        activityMainBinding.operando1.text = ""
        activityMainBinding.operando2.text = ""
        activityMainBinding.operador.text = ""
    }

    fun calculaResultado(view: View) {
        val numbers1 = activityMainBinding.operando1.text.toString().toFloat()
        val numbers2 = activityMainBinding.operando2.text.toString().toFloat()
        val operador = activityMainBinding.operador.text.toString()
        var calculo: Float = 0F
        if(operador == "/")
            calculo = numbers1 / numbers2
        else if (operador == "+")
            calculo = numbers1 + numbers2
        else if (operador == "-")
            calculo = numbers1 - numbers2
        else if (operador == "x")
            calculo = numbers1 * numbers2
        else if (operador == "sqrt")
            calculo = sqrt(numbers1)
        else if (operador == "%")
            calculo = (numbers1 / 100) * numbers2
        else if (operador == "1(x)")
            calculo =  numbers1.pow(numbers2)

        activityMainBinding.operando1.text = calculo.toString()
        activityMainBinding.operador.text = ""
        activityMainBinding.operando2.text = ""
        zerarAtributos()
    }

    fun addOperador(view: View) {
        val numero: Button = view as Button
        var numbers1 = activityMainBinding.operando1.text as String

        //numeros negativos
        if(!this.operador &&
                numero.text.toString() == "-" &&
                activityMainBinding.operando1.text == "")
            activityMainBinding.operando1.text = "-"
        if(this.operador &&
                numero.text.toString() == "-" &&
                activityMainBinding.operando2.text == "" &&
                activityMainBinding.operando1.text != "")
            activityMainBinding.operando2.text = "-"

        //operador menos
        if(!this.operador && numbers1 != ""){
            activityMainBinding.operador.text = numero.text.toString()
            this.operador = true
        } else{
            Toast.makeText(this, "Operador já adicionado ou numero não inserido", Toast.LENGTH_SHORT).show()
        }
    }

    fun inserePonto(view: View) {
        val numero: Button = view as Button
        var numbers1 = activityMainBinding.operando1.text as String
        var numbers2 = activityMainBinding.operando1.text as String

        if(!operador) {
            if(!pontoInserido1 && numbers1 != "" && numbers1.length < 5){
                numbers1 += numero.text as String
                activityMainBinding.operando1.text = numbers1
                this.pontoInserido1 = true
            }
        } else {
            if(!pontoInserido2  && numbers2 != "" && numbers2.length < 5){
                numbers2 += numero.text as String
                activityMainBinding.operando1.text = numbers2
                this.pontoInserido2 = true
            }
        }
    }
}