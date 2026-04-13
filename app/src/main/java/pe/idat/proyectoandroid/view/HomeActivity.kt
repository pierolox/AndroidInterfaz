package pe.idat.proyectoandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
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