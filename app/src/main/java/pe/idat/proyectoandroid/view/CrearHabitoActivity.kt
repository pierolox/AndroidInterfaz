package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import pe.idat.proyectoandroid.data.CategoriaData
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

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            CategoriaData.lista
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCategoria.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnGuardar.setOnClickListener {

            val nombre = binding.etNombre.text.toString().trim()
            val motivacion = binding.etMotivacion.text.toString().trim()
            val recompensa = binding.etRecompensa.text.toString().trim()
            val categoria = binding.spCategoria.selectedItem.toString()

            if (nombre.isEmpty() || motivacion.isEmpty() || recompensa.isEmpty()) {
                AppMensaje.enviarMensaje(binding.root, "Completa todos los campos", TipoMensaje.ERROR)
                return@setOnClickListener
            }

            val habito = Habito(nombre, motivacion, recompensa, categoria)

            HabitoData.lista.add(habito)

            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            CategoriaData.lista
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCategoria.adapter = adapter
    }
}