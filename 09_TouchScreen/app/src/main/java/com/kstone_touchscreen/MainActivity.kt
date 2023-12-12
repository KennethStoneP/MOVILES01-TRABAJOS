package com.kstone_touchscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.MotionEvent
import android.view.View

class MainActivity : AppCompatActivity() {

    // Declaración de variables para TextView y View
    private lateinit var txtTexto1: TextView
    private lateinit var txtTexto2: TextView
    private lateinit var txtTexto3: TextView
    private lateinit var txtTexto4: TextView
    private lateinit var activity_motion_event: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de las variables con las vistas correspondientes
        txtTexto1 = findViewById(R.id.txtTexto1)
        txtTexto2 = findViewById(R.id.txtTexto2)
        txtTexto3 = findViewById(R.id.txtTexto3)
        txtTexto4 = findViewById(R.id.txtTexto4)
        activity_motion_event = findViewById(R.id.activity_motion_event)

        // Establece un oyente táctil en el View principal
        activity_motion_event.setOnTouchListener { v: View, m: MotionEvent ->
            handleTouch(m) // Llama a la función handleTouch con el MotionEvent
            true // Indica que el evento ha sido manejado
        }
    }

    // Función para manejar los eventos táctiles
    private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount
        for (i in 0 until pointerCount) {
            val x = m.getX(i)
            val y = m.getY(i)
            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex
            var actionString: String

            // Asigna una cadena descriptiva a la acción táctil
            when (action) {
                MotionEvent.ACTION_DOWN -> actionString = "Abajo"
                MotionEvent.ACTION_UP -> actionString = "Arriba"
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "Puntero Abajo"
                MotionEvent.ACTION_POINTER_UP -> actionString = "Puntero Arriba"
                MotionEvent.ACTION_MOVE -> actionString = "MOVER"
                else -> actionString = ""
            }

            // Crea una cadena que describe el estado táctil
            val touchStatus =
                "Acción: $actionString Indice: $actionIndex ID: $id X: $x Y: $y"

            // Asigna la cadena descriptiva a los TextView correspondientes según el ID
            when (id) {
                0 -> txtTexto1.text = touchStatus
                1 -> txtTexto2.text = touchStatus
                2 -> txtTexto3.text = touchStatus
                3 -> txtTexto4.text = touchStatus
            }
        }
    }
}
