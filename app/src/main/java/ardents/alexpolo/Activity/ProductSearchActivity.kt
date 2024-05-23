package ardents.alexpolo.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.Adapter.HomeAdapter
import ardents.alexpolo.Model.BestSellingModel
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.FragmentHomeViewModel
import ardents.alexpolo.databinding.ActivityProductSearchBinding
import ardents.alexpolo.utils.NetworkResult

class ProductSearchActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductSearchBinding
    lateinit var viewModel: FragmentHomeViewModel
    val searchlist:List<ProductsItem> = emptyList()
    lateinit var adapter:HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityProductSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        adapter=HomeAdapter(this,searchlist)
        binding.searchProductRecycler.adapter=adapter
      binding.edtSearch.addTextChangedListener(object : TextWatcher {
          override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
             // TODO("Not yet implemented")
          }

          override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              var name=binding.edtSearch.text.toString()
              viewModel.getSearchProduct(name)
          }

          override fun afterTextChanged(p0: Editable?) {
             // TODO("Not yet implemented")
          }
      })

        viewModel.searchProduct.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    Log.d("searchResult",it.data.toString())
                    if (it.data!=null){
                        adapter.updateHomeList(it.data.products)
                        binding.imgNotfound.visibility=View.GONE
                        binding.searchProductRecycler.visibility=View.VISIBLE
                    }else{
                        binding.searchProductRecycler.visibility=View.GONE
                        binding.imgNotfound.visibility=View.VISIBLE
                    }
                }
                is NetworkResult.Error-> Log.d("searchResult",it.message.toString())
                is NetworkResult.Loading->Log.d("searchResult","please try again")
            }
        })




    }
}