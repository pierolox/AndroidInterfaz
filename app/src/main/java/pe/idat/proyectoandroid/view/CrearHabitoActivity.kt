package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import pe.idat.proyectoandroid.data.HabitoData
import pe.idat.proyectoandroid.databinding.ActivityCrearHabitoBinding
import pe.idat.proyectoandroid.model.Habito
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje

class CrearHabitoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearHabitoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearHabitoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categorias = listOf(
            "Personal",
            "Trabajo",
            "Deporte",
            "Familia",
            "Hogar",
            "Salud",
            "Dinero",
            "Varios"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCategoria.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnGuardar.setOnClickListener {

            val nombre = binding.etNombre.text.toString().trim()
            val categoria = binding.spCategoria.selectedItem.toString()

            if (nombre.isEmpty()) {
                binding.etNombre.requestFocus()
                AppMensaje.enviarMensaje(binding.root, "ingrese un nombre", TipoMensaje.ERROR)
                return@setOnClickListener
            }

            val habito = Habito(nombre, categoria)

            HabitoData.lista.add(habito)

            finish()
        }
    }
}