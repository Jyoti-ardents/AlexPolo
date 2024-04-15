package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addressHeader.txtHeader.text=getString(R.string.address)

        binding.addressHeader.btnBack.setOnClickListener {
            finish()
        }
    }
}