package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.RegisterModel
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class RegisterRepo() {
    val _registerData=MutableLiveData<NetworkResult<RegisterModel>>()
    val registerData:LiveData<NetworkResult<RegisterModel>>
        get() = _registerData
    suspend fun registerUser(fname:String ,lname:String ,email:String ,phone:String ,password:String){
        _registerData.postValue(NetworkResult.Loading())
        val response= RetrofitClient.apiServices.register(fname,lname,email,phone,password)
        if (response.isSuccessful && response.body()!=null)
        {
            _registerData.postValue(NetworkResult.Success(response.body()!!))
            Log.d("registerdata","msg==${response.body()}")
        }else if (response.errorBody()!=null){
            //val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
            //Log.d("registerdata","msg==${jsonObj}")
             val gson = Gson()
            //val type = object : TypeToken<Errors>() {}.type
             //val error = gson.fromJson<Errors>(response.errorBody()?.charStream(), type)
             val error = gson.fromJson(response.errorBody()?.charStream(), Errors::class.java)
            Log.d("registerdata","msg==${error}")
            _registerData.postValue(NetworkResult.Error(error.toString()))

        }else{
            _registerData.postValue(NetworkResult.Error("something went wrong"))
        }

    }

}

data class Error(
    @SerializedName("code")
    val code:String,
    @SerializedName("message")
    val message:String
)
data class Errors(
    @SerializedName("errors")
    val error:List<Error>
)