package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.RegisterModel
import ardents.alexpolo.Repository.RegisterRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel():ViewModel() {
    val registerRepo= RegisterRepo()
    val registerData:LiveData<NetworkResult<RegisterModel>>
        get() = registerRepo.registerData
     fun register(fname:String,lname:String,email:String,phone:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            registerRepo.registerUser(fname,lname,email,phone,password)
          //  registerData.po
        }



    }

}