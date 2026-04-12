package pe.idat.proyectoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.idat.proyectoandroid.databinding.ActivityCrearHabitoBinding

class CrearHabitoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearHabitoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearHabitoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}