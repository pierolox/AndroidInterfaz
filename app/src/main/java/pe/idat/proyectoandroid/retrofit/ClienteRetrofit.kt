package pe.idat.proyectoandroid.retrofit

import pe.idat.proyectoandroid.util.Constantes
import pe.idat.proyectoandroid.retrofit.api.ClienteRest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ClienteRetrofit {

    val api: ClienteRest by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClienteRest::class.java)
    }
}