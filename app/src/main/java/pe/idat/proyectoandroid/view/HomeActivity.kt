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
import pe.idat.proyectoandroid.data.HabitoData
import pe.idat.proyectoandroid.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding;

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
            val intent = Intent(this, CrearHabitoActivity::class.java)
            startActivity(intent)
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

        val lista = HabitoData.lista

        if (lista.isEmpty()) {
            binding.tvVacio.visibility = View.VISIBLE
            binding.rvHabitos.visibility = View.GONE
        } else {
            binding.tvVacio.visibility = View.GONE
            binding.rvHabitos.visibility = View.VISIBLE

            binding.rvHabitos.adapter = HabitoAdapter(lista)
        }
    }
}