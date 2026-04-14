package pe.idat.proyectoandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.proyectoandroid.databinding.ItemCategoriaBinding

class CategoriaAdapter(private val lista: List<String>) :
    RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCategoriaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoriaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategoria.text = lista[position]
    }
}