package ardents.alexpolo.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ardents.alexpolo.Activity.MainActivity
import ardents.alexpolo.Adapter.CategoryAdapter
import ardents.alexpolo.Model.CategoryModel
import ardents.alexpolo.R
import ardents.alexpolo.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCategoryBinding.inflate(inflater,container,false)

        binding.categoryHeader.txtHeader.text=getString(R.string.all_categories)

        val categoryList=ArrayList<CategoryModel>()
        categoryList.add(CategoryModel(R.drawable.jeans,"Jeans",))
        categoryList.add(CategoryModel(R.drawable.trouser,"Trouser"))
        categoryList.add(CategoryModel(R.drawable.s4,"Clothes"))
        val adapter= CategoryAdapter(requireContext(),categoryList)
        binding.allcategoryRecycler.adapter=adapter
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryHeader.btnBack.setOnClickListener {
            val intent= Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}