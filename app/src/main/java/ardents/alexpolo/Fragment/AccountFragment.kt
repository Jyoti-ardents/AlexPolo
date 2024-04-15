package ardents.alexpolo.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ardents.alexpolo.Activity.MainActivity
import ardents.alexpolo.R
import ardents.alexpolo.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    private val PICK_IMAGE_REQUEST = 1
    var selectImageUri: Uri?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAccountBinding.inflate(inflater,container, false)

        binding.accountHeader.txtHeader.text=getString(R.string.my_profile)

        if (savedInstanceState != null) {
            selectImageUri = savedInstanceState.getParcelable("selectedImageUri")
            binding.userimg.setImageURI(selectImageUri)
        }
        binding.cardChooseimg.setOnClickListener {
            openGallery()
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountHeader.btnBack.setOnClickListener {
            val intent= Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
    fun openGallery(){
        val galleryIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==PICK_IMAGE_REQUEST &&  resultCode == Activity.RESULT_OK && data != null){
            selectImageUri=data.data
            binding.userimg.setImageURI(selectImageUri)
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("selectedImageUri", selectImageUri)
    }

}