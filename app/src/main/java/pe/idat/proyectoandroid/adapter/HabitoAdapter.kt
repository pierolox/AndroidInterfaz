package pe.idat.proyectoandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.idat.proyectoandroid.R
import pe.idat.proyectoandroid.model.Habito

class HabitoAdapter(private val lista: List<Habito>) :
    RecyclerView.Adapter<HabitoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = view.findViewById(R.id.tvCategoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habito, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habito = lista[position]
        holder.tvNombre.text = habito.nombre
        holder.tvCategoria.text = "Categoría ID: ${habito.categoriaId}"
    }
}