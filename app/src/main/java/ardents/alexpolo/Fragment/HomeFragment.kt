package ardents.alexpolo.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.alexpolo.Adapter.CategoryAdapter
import ardents.alexpolo.Adapter.HomeAdapter
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.ViewModel.FragmentHomeViewModel
import ardents.alexpolo.databinding.FragmentHomeBinding
import ardents.alexpolo.utils.NetworkResult
import com.denzcoskun.imageslider.models.SlideModel


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var viewModel:FragmentHomeViewModel
    lateinit var adapter: CategoryAdapter
   // lateinit var productViewModel: ProductViewModel
    lateinit var bannerList:List<BannerModelItem>
    var categoryList:List<CategoryModelItem> = emptyList()
    var latestProductList:List<ProductsItem> = emptyList()
    var topProductList:List<ProductsItem> = emptyList()
    var bestSellingProductList:List<ProductsItem> = emptyList()
    var discountProductList:List<ProductsItem> = emptyList()
    lateinit var homeAdapter: HomeAdapter
    lateinit var topProducts: HomeAdapter
    lateinit var bestSellingProduct: HomeAdapter
    lateinit var discountProduct: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
     //   productViewModel=ViewModelProvider(this).get(ProductViewModel::class.java)

        viewModel.bannerData.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                bannerList= it.data!!
                var sliderList=ArrayList<SlideModel>()
                for (data in bannerList)
                {
                  sliderList.add(SlideModel(data.image_path))
                }
                binding.imageSlider.setImageList(sliderList)
            }
        })
        viewModel.getBanner()
//        sliderList.add(SlideModel(R.drawable.s2))
//        sliderList.add(SlideModel(R.drawable.s3))
//        binding.imageSlider.setImageList(sliderList)

        adapter= CategoryAdapter(requireContext(),categoryList)
        binding.categoryRecycler.adapter=adapter
        viewModel.getCategory()
        viewModel.categoryData.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                categoryList= it.data!!
                adapter.updateCategoryList(categoryList)
            }

        })

        homeAdapter=HomeAdapter(requireContext(),latestProductList)
        binding.latestProductRecycler.adapter=homeAdapter
        viewModel.getLatestProduct()
        viewModel.latestProduct.observe(viewLifecycleOwner, Observer {
//            if (it!=null){
//                topCollectionList=it.data!!
//                topCollectionAdapter.updateProductList(topCollectionList)
//            }
            when(it){
                is NetworkResult.Success->{
                    Log.d("latestproduct",it.data?.products.toString())
                    homeAdapter.updateHomeList(it.data?.products)
                }
                is NetworkResult.Error->Log.d("latestproduct",it.message.toString())
                is NetworkResult.Loading->Log.d("latestproduct","something went wrong")
            }
        })

        topProducts= HomeAdapter(requireContext(),topProductList)
        binding.topRatedProductRecycler.adapter=topProducts
        viewModel.getTopRatedProduct()
        viewModel.topRatedProducts.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    Log.d("topRatedProduct",it.data?.products.toString())
                    topProducts.updateHomeList(it.data?.products)
                }
                is NetworkResult.Error->Log.d("topRatedProduct",it.message.toString())
                is NetworkResult.Loading->Log.d("topRatedProduct","something went wrong")
            }
        })

        bestSellingProduct= HomeAdapter(requireContext(),bestSellingProductList)
        binding.bestSellingProductRecycler.adapter=bestSellingProduct
        viewModel.getBestSellingProduct()
        viewModel.bestSellingProduct.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    Log.d("topRatedProduct",it.data?.products.toString())
                    bestSellingProduct.updateHomeList(it.data?.products)
                }
                is NetworkResult.Error->Log.d("topRatedProduct",it.message.toString())
                is NetworkResult.Loading->Log.d("topRatedProduct","something went wrong")
            }
        })


        discountProduct= HomeAdapter(requireContext(),discountProductList)
        binding.discountProductRecycler.adapter=discountProduct
        viewModel.getDiscountProduct()
        viewModel.discountProduct.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    Log.d("topRatedProduct",it.data?.products.toString())
                    discountProduct.updateHomeList(it.data?.products)
                }
                is NetworkResult.Error->Log.d("topRatedProduct",it.message.toString())
                is NetworkResult.Loading->Log.d("topRatedProduct","something went wrong")
            }
        })


        return binding.root
    }

}