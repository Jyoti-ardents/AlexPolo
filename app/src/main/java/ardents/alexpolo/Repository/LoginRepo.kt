package ardents.alexpolo.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.DeleteAccountModel
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.UserInfoModel
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult
import ardents.alexpolo.utils.SharedPrefManager

class LoginRepo {
    val _loginData=MutableLiveData<NetworkResult<LoginModel>>()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get()=_loginData

    val _userInfo=MutableLiveData<UserInfoModel>()
    val userInfo:LiveData<UserInfoModel>
        get() = _userInfo

    val _delAccountData=MutableLiveData<NetworkResult<DeleteAccountModel>>()
    val delAccountData:LiveData<NetworkResult<DeleteAccountModel>>
        get() = _delAccountData

    suspend fun loginUser(context: Context,email:String,password:String){
        _loginData.postValue(NetworkResult.Loading())
        val response= RetrofitClient.apiServices.login(email,password)
        if (response.isSuccessful && response.body()!=null)
        {
            SharedPrefManager.getInstance(context).setToken(response.body()!!)
            _loginData.postValue(NetworkResult.Success(response.body()!!))
            Log.d("logindata","mmsg==${response.body()}")

        }else if (response.errorBody()!=null){
            _loginData.postValue(NetworkResult.Error(response.errorBody().toString()!!))
            Log.d("logindata","msg==${response.errorBody().toString()}")
        }else{
            _loginData.postValue(NetworkResult.Error("something went wrong"))
        }
    }

    suspend fun userInfo(context: Context,token:String){
        val response=RetrofitClient.apiServices.userInfo("Bearer "+token)
        if (response.isSuccessful && response.body()!=null){
            SharedPrefManager.getInstance(context).setUserInfo(response.body()!!)
            Log.d("userinfodata","msg==${response.body()}")
        }else{
            Log.d("userinfodata","errormsg==${response.errorBody()?.string()}")
        }
    }

    suspend fun userDeleteAccount(token: String,id:String){
        val response=RetrofitClient.apiServices.deleteAccount(token,id)
        if (response.isSuccessful && response!=null){
            _delAccountData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _delAccountData.postValue(NetworkResult.Error(response.errorBody()?.string()))
        }

    }
}