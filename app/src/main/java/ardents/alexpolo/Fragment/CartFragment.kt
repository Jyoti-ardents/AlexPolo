package ardents.alexpolo.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ardents.alexpolo.Activity.MainActivity
import ardents.alexpolo.R
import ardents.alexpolo.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(inflater,container,false)

        binding.cartHearder.txtHeader.text=getString(R.string.my_cart)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartHearder.btnBack.setOnClickListener {
            val intent= Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }


}