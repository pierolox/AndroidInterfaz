package pe.idat.proyectoandroid.retrofit.api

import pe.idat.proyectoandroid.model.*
import pe.idat.proyectoandroid.retrofit.request.LoginRequest
import pe.idat.proyectoandroid.retrofit.request.RegistroRequest
import pe.idat.proyectoandroid.retrofit.response.LoginResponse
import pe.idat.proyectoandroid.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.http.*

interface ClienteRest {

    @POST("usuario/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("usuario")
    fun registrar(@Body request: RegistroRequest): Call<RegistroResponse>

    @POST("habitos")
    fun crearHabito(@Body habito: Habito): Call<Habito>

    @GET("habitos/usuario/{usuarioId}")
    fun getHabitosPorUsuario(@Path("usuarioId") usuarioId: Long): Call<List<Habito>>

    @POST("categoria")
    fun crearCategoria(@Body categoria: Categoria): Call<Categoria>

    @GET("categoria/usuario/{usuarioId}")
    fun getCategoriasPorUsuario(@Path("usuarioId") usuarioId: Long): Call<List<Categoria>>

    @POST("notas")
    fun crearNota(@Body nota: Nota): Call<Nota>

    @GET("notas/usuario/{usuarioId}")
    fun getNotasPorUsuario(@Path("usuarioId") usuarioId: Long): Call<List<Nota>>
}