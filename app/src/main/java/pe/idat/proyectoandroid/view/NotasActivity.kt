package pe.idat.proyectoandroid.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.proyectoandroid.adapter.NotaAdapter
import pe.idat.proyectoandroid.data.NotaData
import pe.idat.proyectoandroid.databinding.ActivityNotasBinding

class NotasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotas.layoutManager = LinearLayoutManager(this)

        binding.fabAgregarNota.setOnClickListener {
            startActivity(Intent(this, CrearNotaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val lista = NotaData.lista

        if (lista.isEmpty()) {
            binding.tvVacioNotas.visibility = View.VISIBLE
            binding.rvNotas.visibility = View.GONE
        } else {
            binding.tvVacioNotas.visibility = View.GONE
            binding.rvNotas.visibility = View.VISIBLE
            binding.rvNotas.adapter = NotaAdapter(lista)
        }
    }
}