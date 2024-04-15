package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult

class LoginRepo {
    val _loginData=MutableLiveData<NetworkResult<LoginModel>>()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get()=_loginData

    suspend fun loginUser(email:String,password:String){
        _loginData.postValue(NetworkResult.Loading())
        val response= RetrofitClient.apiServices.login(email,password)
        if (response.isSuccessful && response.body()!=null)
        {
            _loginData.postValue(NetworkResult.Success(response.body()!!))
            Log.d("logindata","msg==${response.body()}")
        }else if (response.errorBody()!=null){
            _loginData.postValue(NetworkResult.Error(response.errorBody().toString()!!))
            Log.d("logindata","msg==${response.errorBody().toString()}")
        }else{
            _loginData.postValue(NetworkResult.Error("something went wrong"))
        }
    }
}