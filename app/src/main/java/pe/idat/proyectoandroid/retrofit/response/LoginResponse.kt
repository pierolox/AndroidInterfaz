package pe.idat.proyectoandroid.retrofit.response

data class LoginResponse(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String
)
