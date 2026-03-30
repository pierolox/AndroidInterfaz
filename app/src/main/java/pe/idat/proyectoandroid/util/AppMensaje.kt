package pe.idat.proyectoandroid.util

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import pe.idat.proyectoandroid.R

object AppMensaje {
    fun enviarMensaje(vista: View,
                      mensaje: String,
                      tipoMensaje: TipoMensaje
    ) {
        val snackbar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)

        if (tipoMensaje == TipoMensaje.ERROR) {
            snackbar.setBackgroundTint(ContextCompat.getColor(
                MiApp.Companion.instance,
                R.color.error))
        } else {
            snackbar.setBackgroundTint(ContextCompat.getColor(
                MiApp.Companion.instance,
                R.color.success))
        }
        snackbar.show()
    }
}