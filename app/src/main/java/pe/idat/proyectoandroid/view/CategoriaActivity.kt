package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.idat.proyectoandroid.adapter.CategoriaAdapter
import pe.idat.proyectoandroid.data.CategoriaData
import pe.idat.proyectoandroid.databinding.ActivityCategoriaBinding

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCategorias.layoutManager = LinearLayoutManager(this)
        binding.rvCategorias.adapter = CategoriaAdapter(CategoriaData.lista)

        // BOTÓN VOLVER
        binding.btnBack.setOnClickListener {
            finish()
        }

        // GUARDAR CATEGORÍA
        binding.btnGuardarCategoria.setOnClickListener {

            val nueva = binding.etNuevaCategoria.text.toString().trim()

            if (nueva.isEmpty()) {
                binding.etNuevaCategoria.requestFocus()
                return@setOnClickListener
            }

            CategoriaData.lista.add(nueva)

            binding.etNuevaCategoria.setText("")
            binding.rvCategorias.adapter?.notifyDataSetChanged()
        }
    }
}