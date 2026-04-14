package pe.idat.proyectoandroid.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.idat.proyectoandroid.databinding.ActivityMainBinding
import pe.idat.proyectoandroid.retrofit.ClienteRetrofit
import pe.idat.proyectoandroid.retrofit.request.LoginRequest
import pe.idat.proyectoandroid.retrofit.response.LoginResponse
import pe.idat.proyectoandroid.util.AppMensaje
import pe.idat.proyectoandroid.util.TipoMensaje
import pe.idat.proyectoandroid.util.UsuarioSesion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener(this)

        binding.btnRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        login()
    }

    private fun login() {

        val correo = binding.etusuario.text.toString().trim()
        val password = binding.etpassword.text.toString().trim()

        if (correo.isEmpty()) {
            binding.etusuario.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese su correo", TipoMensaje.ERROR)
            return
        }

        if (password.isEmpty()) {
            binding.etpassword.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese su password", TipoMensaje.ERROR)
            return
        }

        val request = LoginRequest(correo, password)

        ClienteRetrofit.api.login(request).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {

                if (response.isSuccessful && response.body() != null) {

                    val user = response.body()

                    UsuarioSesion.id = user?.id?.toLong() ?: 0
                    println("LOGIN ID: ${UsuarioSesion.id}")

                    AppMensaje.enviarMensaje(
                        binding.root,
                        "Bienvenido ${user?.nombre}",
                        TipoMensaje.SUCCESS
                    )

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()

                } else {
                    AppMensaje.enviarMensaje(
                        binding.root,
                        "Credenciales incorrectas",
                        TipoMensaje.ERROR
                    )
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ERROR_API", t.message.toString())

                AppMensaje.enviarMensaje(
                    binding.root,
                    "Error de conexión",
                    TipoMensaje.ERROR
                )
            }
        })
    }
}
