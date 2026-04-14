package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.idat.proyectoandroid.data.NotaData
import pe.idat.proyectoandroid.databinding.ActivityCrearNotaBinding
import pe.idat.proyectoandroid.model.Nota
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje

class CrearNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearNotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarNota.setOnClickListener {

            val titulo = binding.etTitulo.text.toString().trim()
            val contenido = binding.etContenido.text.toString().trim()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                AppMensaje.enviarMensaje(binding.root, "Complete todos los campos", TipoMensaje.ERROR)
                return@setOnClickListener
            }

            NotaData.lista.add(Nota(titulo, contenido))

            finish()
        }
    }
}