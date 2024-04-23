package ardents.alexpolo.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ardents.alexpolo.Activity.MainActivity
import ardents.alexpolo.Model.ProfileUpdateRequest
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.AccountViewModel
import ardents.alexpolo.databinding.FragmentAccountBinding
import ardents.alexpolo.utils.Helper
import ardents.alexpolo.utils.NetworkResult
import ardents.alexpolo.utils.SharedPrefManager

class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    private val PICK_IMAGE_REQUEST = 1
    var selectImageUri: Uri? = null
    lateinit var accountViewModel:AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        accountViewModel= ViewModelProviders.of(requireActivity()).get(AccountViewModel::class.java)

        binding.accountHeader.txtHeader.text = getString(R.string.my_profile)

        if (savedInstanceState != null) {
            selectImageUri = savedInstanceState.getParcelable("selectedImageUri")
            binding.userimg.setImageURI(selectImageUri)
        }
        binding.cardChooseimg.setOnClickListener {
            openGallery()
        }

        // Toast.makeText(requireContext(), SharedPrefManager.getInstance(requireContext()).getUserInfo().email, Toast.LENGTH_SHORT).show()
        accountViewModel.profileData.observe(viewLifecycleOwner, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    Toast.makeText(requireContext(),it.data.toString(),Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(requireContext())
                }
            }

        })


        binding.btnUpdate.setOnClickListener {
            val fname=binding.fname.text.toString().trim()
            val lname=binding.lname.text.toString().trim()
            val phone=binding.phone.text.toString().trim()
            if (!Helper.validateEditText(binding.fname)||
                !Helper.validateEditText(binding.lname)||
                !Helper.validateEditText(binding.phone)){
                return@setOnClickListener
            }else{
                SharedPrefManager.getInstance(requireContext()).getToken()?.let { it1 ->
                    accountViewModel.updateProfile(requireContext(), it1.token,
                        ProfileUpdateRequest(fname,lname,phone)
                    )
                }
            }


        }
        showProfile()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountHeader.btnBack.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectImageUri = data.data
            binding.userimg.setImageURI(selectImageUri)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("selectedImageUri", selectImageUri)
    }

    fun showProfile() {
        val userInfo = SharedPrefManager.getInstance(requireContext()).getUserInfo()
        binding.fname.text = Editable.Factory.getInstance().newEditable(userInfo.f_name)
        binding.lname.text = Editable.Factory.getInstance().newEditable(userInfo.l_name)
        binding.phone.text = Editable.Factory.getInstance().newEditable(userInfo.phone)

    }


}