package pe.idat.proyectoandroid.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import pe.idat.proyectoandroid.databinding.ActivityCrearHabitoBinding
import pe.idat.proyectoandroid.model.Habito
import pe.idat.proyectoandroid.retrofit.ClienteRetrofit
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje
import pe.idat.proyectoandroid.util.UsuarioSesion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrearHabitoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearHabitoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearHabitoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categorias = listOf("Personal", "Trabajo", "Deporte")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorias
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategoria.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnGuardar.setOnClickListener {

            val nombre = binding.etNombre.text.toString().trim()
            val posicion = binding.spCategoria.selectedItemPosition

            if (nombre.isEmpty()) {
                AppMensaje.enviarMensaje(binding.root, "Ingrese un nombre", TipoMensaje.ERROR)
                return@setOnClickListener
            }

            if (UsuarioSesion.id == 0L) {
                AppMensaje.enviarMensaje(binding.root, "Usuario no válido", TipoMensaje.ERROR)
                return@setOnClickListener
            }

            val habito = Habito(
                nombre = nombre,
                categoriaId = (posicion + 1).toLong(),
                usuarioId = UsuarioSesion.id
            )

            ClienteRetrofit.api.crearHabito(habito)
                .enqueue(object : Callback<Habito> {

                    override fun onResponse(
                        call: Call<Habito>,
                        response: Response<Habito>
                    ) {

                        if (response.isSuccessful) {

                            AppMensaje.enviarMensaje(
                                binding.root,
                                "Hábito guardado",
                                TipoMensaje.SUCCESS
                            )

                            finish()

                        } else {

                            AppMensaje.enviarMensaje(
                                binding.root,
                                "Error al guardar",
                                TipoMensaje.ERROR
                            )
                        }
                    }

                    override fun onFailure(call: Call<Habito>, t: Throwable) {

                        AppMensaje.enviarMensaje(
                            binding.root,
                            "Error conexión: ${t.message}",
                            TipoMensaje.ERROR
                        )
                    }
                })
        }
    }
}