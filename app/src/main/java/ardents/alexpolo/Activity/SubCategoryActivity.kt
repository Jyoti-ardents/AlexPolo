package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.Adapter.SubCatAdapter
import ardents.alexpolo.Adapter.ProductAdapter
import ardents.alexpolo.Model.ChildesItem
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.SubCategoryModel
import ardents.alexpolo.ViewModel.ProductViewModel
import ardents.alexpolo.databinding.ActivitySubCategoryBinding
import ardents.alexpolo.utils.Constant
import ardents.alexpolo.utils.NetworkResult
import ardents.alexpolo.utils.SharedPrefManager

class SubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryBinding
    //val subCatList:ArrayList<ChildesItem> = Constant.subCategoryData
    var subCatList:ArrayList<ChildesItem>?=null
    lateinit var viewModel: ProductViewModel
    lateinit var  productAdapter:ProductAdapter
    var productList:List<ProductModelItem> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(ProductViewModel::class.java)
        val categoryName=intent.getStringExtra("categoryName")
        val categoryId=intent.getStringExtra("categoryId")
        Log.d("hellomydata",subCatList.toString())
        binding.subCategoryHeader.txtHeader.text=categoryName

        subCatList=SharedPrefManager.getInstance(this).getChildesList()



        productAdapter=ProductAdapter(applicationContext,productList)
        binding.productRecycler.adapter=productAdapter

        val allOption=subCatList?.find { it?.name=="All" }
        if (allOption == null){
            subCatList?.add(0,ChildesItem(name = "All", id = categoryId?.toInt()))
        }
        val adapter=SubCatAdapter(applicationContext,subCatList!!){
            // viewModel.getProduct(subcategoryId.toString())
            viewModel.getProduct(it.id.toString())
        }
        binding.subcatRecycler.adapter=adapter





        viewModel.productData.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    productList= it.data!!
                    productAdapter.updateProductList(productList)
                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Toast.makeText(this,"Please Wait",Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this,"Please Wait",Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getProduct(categoryId.toString())




        binding.subCategoryHeader.btnBack.setOnClickListener {
            finish()
        }



    }
}