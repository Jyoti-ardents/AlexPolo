package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.ForgotPasswordViewModel
import ardents.alexpolo.databinding.ActivityForgetPasswordBinding
import ardents.alexpolo.utils.Helper
import ardents.alexpolo.utils.NetworkResult

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordBinding
    lateinit var viewModel: ForgotPasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.forgotpassHeader.txtHeader.text=getString(R.string.forgot_pass)
//        binding.forgotpassHeader.btnBack.setOnClickListener {
//            finish()
//        }

        viewModel= ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        viewModel.forgotData.observe(this) {
            Helper.dismissProgressDialog()
            when (it) {
                is NetworkResult.Success -> {
                    // Toast.makeText(this,  it.data?.toString(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Check your mail", Toast.LENGTH_SHORT).show()
                    Log.d("passworddata", "mail msg==${it.data}")
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    Helper.showProgressDialog(this)
                }
            }
        }


        binding.btnSubmit.setOnClickListener {
            val mail=binding.resetpassMail.text.toString()
            if (!Helper.validateEditText(binding.resetpassMail)){
                return@setOnClickListener
            }else{
                viewModel.forgotPass(binding.resetpassMail.text.toString())
            }

            binding.resetpassMail.text=null
        }


    }
}