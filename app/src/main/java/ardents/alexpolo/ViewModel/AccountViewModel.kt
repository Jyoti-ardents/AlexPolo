package ardents.alexpolo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.ProfileUpdateModel
import ardents.alexpolo.Model.ProfileUpdateRequest
import ardents.alexpolo.Repository.AccountRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel:ViewModel() {
    val accountRepo=AccountRepo()
    val profileData:LiveData<NetworkResult<ProfileUpdateModel>>
        get() = accountRepo._profileData

    fun updateProfile(context: Context,token:String,body:ProfileUpdateRequest){
        viewModelScope.launch(Dispatchers.IO) {
            accountRepo.udateProfile(context,token, body)
        }
    }
}