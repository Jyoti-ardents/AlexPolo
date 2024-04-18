package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.LoginViewModel
import ardents.alexpolo.databinding.ActivityLoginBinding
import ardents.alexpolo.utils.Helper
import ardents.alexpolo.utils.NetworkResult

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.loginData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                is NetworkResult.Error->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }

        })
        binding.btnLogin.setOnClickListener {
            val mail=binding.loginMail.text.toString()
            val password=binding.loginPasswd.text.toString()
            if (!Helper.isValidMail(binding.loginMail)||
                !Helper.validateEditText(binding.loginPasswd)){
                return@setOnClickListener
            }else{
                viewModel.login(applicationContext,mail,password)
            }
            binding.loginMail.text=null
            binding.loginPasswd.text=null

        }

        binding.txtSignup.setOnClickListener {
            val intent= Intent(applicationContext,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.hideLoginPasswd.setOnClickListener {
            var cursorPosition:Int=binding.loginPasswd.selectionStart
            binding.loginPasswd.transformationMethod= HideReturnsTransformationMethod.getInstance()
            binding.hideLoginPasswd.visibility= View.GONE
            binding.showLoginPasswd.visibility= View.VISIBLE
            binding.loginPasswd.setSelection(cursorPosition)
        }
        binding.showLoginPasswd.setOnClickListener {
            var cursorPosition:Int=binding.loginPasswd.selectionStart
            binding.loginPasswd.transformationMethod= PasswordTransformationMethod.getInstance()
            binding.hideLoginPasswd.visibility= View.VISIBLE
            binding.showLoginPasswd.visibility= View.GONE
            binding.loginPasswd.setSelection(cursorPosition)

        }


        binding.forgotpass.setOnClickListener {
            startActivity(Intent(applicationContext,ForgetPasswordActivity::class.java))
        }

    }
}