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
import ardents.alexpolo.ViewModel.RegisterViewModel
import ardents.alexpolo.databinding.ActivitySignUpBinding
import ardents.alexpolo.utils.Helper
import ardents.alexpolo.utils.NetworkResult

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
            finish()
        }
        viewModel.registerData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                    finish()
                }
                is NetworkResult.Error->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }
        })


        binding.btnRegister.setOnClickListener {
            val fname=binding.registerFname.text.toString().trim()
            val lname=binding.registerLname.text.toString().trim()
            val phone=binding.registerPhone.text.toString().trim()
            val mail=binding.registerEmail.text.toString().trim()
            val password=binding.registerPasswd.text.toString()
            if (!Helper.validateEditText(binding.registerFname) ||
                !Helper.validateEditText(binding.registerLname)||
                !Helper.isValidMail(binding.registerEmail)||
                !Helper.validateEditText(binding.registerPhone)||
                !Helper.validateEditText(binding.registerPasswd)){
                return@setOnClickListener
            }else{
                viewModel.register(fname,lname,mail,phone,password)
            }
            binding.registerFname.text=null
            binding.registerLname.text=null
            binding.registerEmail.text=null
            binding.registerPhone.text=null
            binding.registerPasswd.text=null

        }

        binding.hideRegisterPasswd.setOnClickListener {
            var cursorPosition:Int=binding.registerPasswd.selectionStart
            binding.registerPasswd.transformationMethod= HideReturnsTransformationMethod.getInstance()
            binding.hideRegisterPasswd.visibility= View.GONE
            binding.showRegisterPasswd.visibility= View.VISIBLE
            binding.registerPasswd.setSelection(cursorPosition)
        }
        binding.showRegisterPasswd.setOnClickListener {
            var cursorPosition:Int=binding.registerPasswd.selectionStart
            binding.registerPasswd.transformationMethod= PasswordTransformationMethod.getInstance()
            binding.hideRegisterPasswd.visibility= View.VISIBLE
            binding.showRegisterPasswd.visibility= View.GONE
            binding.registerPasswd.setSelection(cursorPosition)

        }
    }

}