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
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.R
import ardents.alexpolo.ViewModel.FragmentHomeViewModel
import ardents.alexpolo.databinding.FragmentHomeBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var viewModel:FragmentHomeViewModel
    lateinit var adapter: CategoryAdapter
    lateinit var bannerList:List<BannerModelItem>
    var categoryList:List<CategoryModelItem> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this).get(FragmentHomeViewModel::class.java)
        Log.d("hellomydata","hello")

//        viewModel.bannerData.observe(viewLifecycleOwner, Observer {
//            val data:List<BannerModelItem> = it
//            var sliderList=ArrayList<SlideModel>()
//            for (item in data){
//               // sliderList.add(SlideModel(,ScaleTypes.FIT))
//            }
//            binding.imageSlider.setImageList(sliderList)
//
//        })
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

        return binding.root
    }

}