package ardents.alexpolo.utils

import android.content.Context
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.ViewModel.LoginViewModel

class SharedPrefManager(context: Context) {
    private val SHARED_PREF_NAME_LOGIN="login"
    val LOGIN_TOKEN="token"
    lateinit var mContext: Context
    init {
        mContext=context
    }
    companion object{
        private var mInstance:SharedPrefManager?=null

        @Synchronized
        fun getInstance(context: Context):SharedPrefManager{
            if (mInstance==null){
                mInstance= SharedPrefManager(context)
            }
            return mInstance!!
        }
    }

    fun setToken(loginModel: LoginModel){
        val sharedPreference=mContext.getSharedPreferences(SHARED_PREF_NAME_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreference.edit()
        editor.putString(LOGIN_TOKEN,loginModel.temporary_token)
        editor.apply()
    }
    fun getToken():LoginModel?{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_LOGIN,Context.MODE_PRIVATE)
        val token=sharedPreferences.getString(LOGIN_TOKEN,null)
        return token?.let { LoginModel(it) }
//        return if (token!=null){
//            LoginModel(token)
//        }else{
//            LoginModel("")
//        }

    }

    fun logout(){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }





}