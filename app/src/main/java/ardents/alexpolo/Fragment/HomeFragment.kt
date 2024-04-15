package ardents.alexpolo.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ardents.alexpolo.Adapter.CategoryAdapter
import ardents.alexpolo.Model.CategoryModel
import ardents.alexpolo.R
import ardents.alexpolo.databinding.FragmentHomeBinding
import com.denzcoskun.imageslider.models.SlideModel


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)

        val sliderList=ArrayList<SlideModel>()
        sliderList.add(SlideModel(R.drawable.s2))
        sliderList.add(SlideModel(R.drawable.s3))
        binding.imageSlider.setImageList(sliderList)

        val categoryList=ArrayList<CategoryModel>()
        categoryList.add(CategoryModel(R.drawable.jeans,"Jeans"))
        categoryList.add(CategoryModel(R.drawable.trouser,"Trouser"))
        categoryList.add(CategoryModel(R.drawable.s4,"Clothes"))
        val adapter= CategoryAdapter(requireContext(),categoryList)
        binding.categoryRecycler.adapter=adapter

        return binding.root
    }

}