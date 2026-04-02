package pe.idat.proyectoandroid.retrofit.api

import pe.idat.proyectoandroid.retrofit.request.LoginRequest
import pe.idat.proyectoandroid.retrofit.request.RegistroRequest
import pe.idat.proyectoandroid.retrofit.response.LoginResponse
import pe.idat.proyectoandroid.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ClienteRest {
    @POST("usuario/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("usuario")
    fun registrar(@Body request: RegistroRequest): Call<RegistroResponse>
}