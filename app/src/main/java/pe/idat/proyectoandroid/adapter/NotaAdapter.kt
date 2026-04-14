package pe.idat.proyectoandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.proyectoandroid.databinding.ItemNotaBinding
import pe.idat.proyectoandroid.model.Nota

class NotaAdapter(private val lista: List<Nota>) :
    RecyclerView.Adapter<NotaAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNotaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nota = lista[position]
        holder.binding.tvNota.text = "${nota.nombre}\n${nota.contenido}"
    }

    override fun getItemCount() = lista.size
}