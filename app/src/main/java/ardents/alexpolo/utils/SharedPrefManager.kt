package ardents.alexpolo.utils

import android.content.Context
import ardents.alexpolo.Model.LoginModel
import ardents.alexpolo.Model.UserInfoModel
import ardents.alexpolo.ViewModel.LoginViewModel

class SharedPrefManager(context: Context) {
    private val SHARED_PREF_NAME_LOGIN="login"
    private val SHARED_PREF_NAME_USERINFO="userInfo"
    val LOGIN_TOKEN="token"
    val USER_FNAME="fname"
    val USER_LNAME="lname"
    val USER_EMAIL="email"
    val USER_PHONE="phone"
    val USER_ID="id"

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
        editor.putString(LOGIN_TOKEN,loginModel.token)
        editor.apply()
    }
    fun getToken():LoginModel?{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_LOGIN,Context.MODE_PRIVATE)
        val token=sharedPreferences.getString(LOGIN_TOKEN,null)
        return token?.let { LoginModel(it) }

    }

    fun setUserInfo(userInfoModel: UserInfoModel){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_USERINFO,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(USER_EMAIL,userInfoModel.email)
        editor.putString(USER_FNAME,userInfoModel.f_name)
        editor.putString(USER_ID,userInfoModel.id.toString())
        editor.putString(USER_LNAME,userInfoModel.l_name)
        editor.putString(USER_PHONE,userInfoModel.phone)

        editor.apply()
    }
    fun getUserInfo():UserInfoModel{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_USERINFO,Context.MODE_PRIVATE)
        return UserInfoModel(
            sharedPreferences.getString(USER_EMAIL,null)!!,
            sharedPreferences.getString(USER_FNAME,null)!!,
            sharedPreferences.getString(USER_ID,"0")!!,
            sharedPreferences.getString(USER_LNAME,null)!!,
            sharedPreferences.getString(USER_PHONE,null)!!

        )
    }

    fun logout(){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }





}