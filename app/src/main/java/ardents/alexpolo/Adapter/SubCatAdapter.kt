package ardents.alexpolo.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ardents.alexpolo.Activity.ProductActivity
import ardents.alexpolo.Model.ChildesItem
import ardents.alexpolo.Model.SubCatModel
import ardents.alexpolo.R
import ardents.alexpolo.databinding.SubcatlayBinding
import com.google.android.material.chip.Chip

class SubCatAdapter(val context: Context, val subcatList: List<ChildesItem>,private val onItemClick: (ChildesItem) -> Unit) :

    RecyclerView.Adapter<SubCatAdapter.ViewHolder>() {
    var index=-1
    class ViewHolder(val binding: SubcatlayBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = SubcatlayBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return subcatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subcat=subcatList[position]
        holder.binding.subcatName.text=subcat.name
        holder.binding.subcatName.setOnClickListener {
            onItemClick(subcat)
            index=position
            notifyDataSetChanged()
        }
        holder.binding.subcatName.isSelected = index == position

        if (index==position){
            holder.binding.subcatName.setBackgroundResource(R.drawable.selected_chipbg)
            holder.binding.subcatName.setTextColor(ContextCompat.getColor(context,R.color.white))
        }else{
            holder.binding.subcatName.setBackgroundResource(R.drawable.unselected_chipbg)
            holder.binding.subcatName.setTextColor(ContextCompat.getColor(context,R.color.black))
        }

    }
}