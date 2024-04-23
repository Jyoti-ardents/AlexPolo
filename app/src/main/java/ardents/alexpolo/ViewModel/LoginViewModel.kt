package ardents.alexpolo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.UserInfoModel
import ardents.alexpolo.Repository.LoginRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val loginRepo= LoginRepo()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get()=loginRepo.loginData

    val userInfo:LiveData<UserInfoModel>
        get() = loginRepo.userInfo

    fun login(context: Context,email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepo.loginUser(context,email,password)
        }

    }
    fun userData(context: Context,token:String){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepo.userInfo(context,token)
        }
    }
}