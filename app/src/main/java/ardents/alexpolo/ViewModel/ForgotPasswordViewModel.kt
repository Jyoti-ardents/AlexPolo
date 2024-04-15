package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.ForgotPassword
import ardents.alexpolo.Repository.ForgotPasswordRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.launch

class ForgotPasswordViewModel:ViewModel() {
    val forgotPasswordRepo= ForgotPasswordRepo()
    val forgotData:LiveData<NetworkResult<ForgotPassword>>
        get() = forgotPasswordRepo.forgotPassData

    fun forgotPass(email:String){
        viewModelScope.launch {
            forgotPasswordRepo.forgotPass(email)
        }
    }

}