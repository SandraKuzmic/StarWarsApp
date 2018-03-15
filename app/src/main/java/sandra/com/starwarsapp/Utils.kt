package sandra.com.starwarsapp

import android.view.View
import android.widget.ProgressBar

fun getDataId(url: String)= url.takeLast(3).substring(1, 2).toInt()

fun visibilityProgressBar(pbLoad: ProgressBar, visible: Boolean) {
    pbLoad.visibility = if (visible) View.VISIBLE else View.GONE
}
