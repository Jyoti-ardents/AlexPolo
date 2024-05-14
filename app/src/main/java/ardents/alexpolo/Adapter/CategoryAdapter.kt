package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.SubCategoryActivity
import ardents.alexpolo.Model.CategoryModel
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.Model.ChildesItem
import ardents.alexpolo.databinding.CategoryLayoutBinding
import ardents.alexpolo.utils.Constant
import com.bumptech.glide.Glide

class CategoryAdapter(val context: Context,var categoryList:List<CategoryModelItem>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: CategoryLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }
    fun updateCategoryList(list:List<CategoryModelItem>){
        categoryList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater=LayoutInflater.from(context)
        val binding=CategoryLayoutBinding.inflate(layoutInflater,parent,false)
        val viewHolder= ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context).load(categoryList.get(position).image_path).into(holder.binding.categoryImg)
        holder.binding.categoryName.text=categoryList.get(position).name
        holder.binding.cardCategory.setOnClickListener {
            val intent=Intent(context, SubCategoryActivity::class.java)
            Constant.subCategoryData= categoryList.get(position).childes as ArrayList<ChildesItem>

            intent.putExtra("categoryName",categoryList.get(position).name)
            intent.putExtra("categoryId",categoryList.get(position).id.toString())
            context.startActivity(intent)

        }
    }
}