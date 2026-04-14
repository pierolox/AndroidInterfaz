package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.idat.proyectoandroid.databinding.ActivityCrearNotaBinding
import pe.idat.proyectoandroid.model.Nota
import pe.idat.proyectoandroid.retrofit.ClienteRetrofit
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje
import pe.idat.proyectoandroid.util.UsuarioSesion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                AppMensaje.enviarMensaje(
                    binding.root,
                    "Complete todos los campos",
                    TipoMensaje.ERROR
                )
                return@setOnClickListener
            }

            val nota = Nota(
                nombre = titulo,
                contenido = contenido,
                usuarioId = UsuarioSesion.id
            )

            ClienteRetrofit.api.crearNota(nota)
                .enqueue(object : Callback<Nota> {

                    override fun onResponse(
                        call: Call<Nota>,
                        response: Response<Nota>
                    ) {
                        if (response.isSuccessful) {

                            AppMensaje.enviarMensaje(
                                binding.root,
                                "Nota creada",
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

                    override fun onFailure(call: Call<Nota>, t: Throwable) {
                        t.printStackTrace()

                        AppMensaje.enviarMensaje(
                            binding.root,
                            "Error de conexión",
                            TipoMensaje.ERROR
                        )
                    }
                })
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}