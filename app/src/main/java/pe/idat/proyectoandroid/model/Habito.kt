package pe.idat.proyectoandroid.model

data class Habito (
    val id: Long? = null,
    val nombre: String,
    val categoriaId: Long,
    val usuarioId: Long
)