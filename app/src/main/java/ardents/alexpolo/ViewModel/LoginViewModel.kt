package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Repository.LoginRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val loginRepo= LoginRepo()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get()=loginRepo.loginData

    fun login(email:String,password:String){
        viewModelScope.launch {
            loginRepo.loginUser(email,password)
        }

    }
}