package open.geosolve.geosolve.ui.canvas

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ToolAbstraction(
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)