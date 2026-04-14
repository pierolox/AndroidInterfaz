package pe.idat.proyectoandroid.view

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.idat.proyectoandroid.databinding.ActivityRegistroBinding
import pe.idat.proyectoandroid.retrofit.ClienteRetrofit
import pe.idat.proyectoandroid.retrofit.request.RegistroRequest
import pe.idat.proyectoandroid.retrofit.response.RegistroResponse
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegistrar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        registrar()
    }

    private fun registrar() {
        val nombre = binding.etNombre.text.toString().trim()
        val correo = binding.etCorreo.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (nombre.isEmpty()) {
            binding.etNombre.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese su nombre", TipoMensaje.ERROR)
            return
        }

        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.etCorreo.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese un correo válido", TipoMensaje.ERROR)
            return
        }

        if (password.isEmpty() || password.length < 6) {
            binding.etPassword.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "La contraseña debe tener al menos 6 caracteres", TipoMensaje.ERROR)
            return
        }

        val request = RegistroRequest(nombre, correo, password)

        ClienteRetrofit.api.registrar(request)
            .enqueue(object : Callback<RegistroResponse> {

                override fun onResponse(
                    call: Call<RegistroResponse>,
                    response: Response<RegistroResponse>
                ) {
                    if (response.isSuccessful) {

                        val usuario = response.body()

                        AppMensaje.enviarMensaje(
                            binding.root,
                            "Usuario registrado: ${usuario?.nombre}",
                            TipoMensaje.SUCCESS
                        )

                        finish()
                    } else {
                        AppMensaje.enviarMensaje(
                            binding.root,
                            "Error al registrar. Intente nuevamente",
                            TipoMensaje.ERROR
                        )
                    }
                }

                override fun onFailure(call: Call<RegistroResponse>, t: Throwable) {
                    AppMensaje.enviarMensaje(
                        binding.root,
                        "Error de conexión con el servidor",
                        TipoMensaje.ERROR
                    )
                }
            })
    }
}
