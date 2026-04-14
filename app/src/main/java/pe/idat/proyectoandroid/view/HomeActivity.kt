package pe.idat.proyectoandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.proyectoandroid.R
import pe.idat.proyectoandroid.adapter.HabitoAdapter
import pe.idat.proyectoandroid.databinding.ActivityHomeBinding
import pe.idat.proyectoandroid.model.Habito
import pe.idat.proyectoandroid.retrofit.ClienteRetrofit
import pe.idat.proyectoandroid.util.UsuarioSesion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvHabitos.layoutManager = LinearLayoutManager(this)

        binding.fabAgregar.setOnClickListener {
            startActivity(Intent(this, CrearHabitoActivity::class.java))
        }

        binding.button.setOnClickListener {

            val popup = PopupMenu(this, binding.button)
            popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {

                    R.id.op_notas -> {
                        startActivity(Intent(this, NotasActivity::class.java))
                        true
                    }

                    R.id.op_categorias -> {
                        startActivity(Intent(this, CategoriaActivity::class.java))
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }
    }

    override fun onResume() {
        super.onResume()

        if (UsuarioSesion.id == 0L) {
            binding.tvVacio.visibility = View.VISIBLE
            binding.rvHabitos.visibility = View.GONE
            return
        }

        ClienteRetrofit.api.getHabitosPorUsuario(UsuarioSesion.id)
            .enqueue(object : Callback<List<Habito>> {

                override fun onResponse(
                    call: Call<List<Habito>>,
                    response: Response<List<Habito>>
                ) {

                    if (response.isSuccessful) {

                        val lista = response.body() ?: emptyList()

                        if (lista.isEmpty()) {
                            binding.tvVacio.visibility = View.VISIBLE
                            binding.rvHabitos.visibility = View.GONE
                        } else {
                            binding.tvVacio.visibility = View.GONE
                            binding.rvHabitos.visibility = View.VISIBLE
                            binding.rvHabitos.adapter = HabitoAdapter(lista)
                        }

                    } else {
                        binding.tvVacio.visibility = View.VISIBLE
                        binding.rvHabitos.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<Habito>>, t: Throwable) {
                    binding.tvVacio.visibility = View.VISIBLE
                    binding.rvHabitos.visibility = View.GONE
                }
            })
    }
}