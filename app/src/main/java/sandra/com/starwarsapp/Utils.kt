package sandra.com.starwarsapp

import android.view.View
import java.util.regex.Pattern

fun getDataId(url: String): Int {
    val p = Pattern.compile("/(\\d+)/$")
    val matcher = p.matcher(url)
    if (matcher.find()) {
        return matcher.group(1).toString().toInt()
    }
    return -1
}

fun visibilityOfView(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
