package ardents.alexpolo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.Network.ApiServices


class ViewModelFactory(private val apiServices: ApiServices) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}