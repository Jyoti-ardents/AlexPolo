package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.ForgotPassword
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult
import com.google.gson.Gson

class ForgotPasswordRepo {

    val _forgotPassData=MutableLiveData<NetworkResult<ForgotPassword>>()
    val forgotPassData:LiveData<NetworkResult<ForgotPassword>>
        get() = _forgotPassData

    suspend fun forgotPass(email:String){
        _forgotPassData.postValue(NetworkResult.Loading())
        val response= RetrofitClient.apiServices.forgotPassword(email)
        if (response.isSuccessful && response.body()!=null){
            Log.d("forgotpassdata","msg==${response.body()}")
           // val gson= Gson()
           // val response=gson.fromJson(response.body().toString(),ForgotPassword::class.java)
           _forgotPassData.postValue(NetworkResult.Success(response.body()!!))
           // Log.d("forgotpassdata","message==$response")

        }else if(response.errorBody()!=null) {
            val gson=Gson()
            val error=gson.fromJson(response.errorBody()?.charStream(),Errors::class.java)
            _forgotPassData.postValue(NetworkResult.Error(error.toString()))
            Log.d("forgotpassdata","message==$error")
        }
        else{
            _forgotPassData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}