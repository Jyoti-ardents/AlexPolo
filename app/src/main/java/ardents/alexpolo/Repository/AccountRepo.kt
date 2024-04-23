package ardents.alexpolo.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.ProfileUpdateModel
import ardents.alexpolo.Model.ProfileUpdateRequest
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult

class AccountRepo {

    val _profileData=MutableLiveData<NetworkResult<ProfileUpdateModel>>()
    val profileData:LiveData<NetworkResult<ProfileUpdateModel>>
        get()=_profileData

    suspend fun udateProfile(context: Context,token:String,body:ProfileUpdateRequest){
        _profileData.postValue(NetworkResult.Loading())
        val response=RetrofitClient.apiServices.updateProfile("Bearer "+token,body)
        if (response.isSuccessful && response.body()!=null){
            Log.d("profiledata","msg==${response.body()}")
            _profileData.postValue(NetworkResult.Success(response.body()!!))
        }else if (response.errorBody()!=null){
            _profileData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            Log.d("profiledata","errormsg==${response.errorBody()?.string()}")
        }else{
            _profileData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

    }
}