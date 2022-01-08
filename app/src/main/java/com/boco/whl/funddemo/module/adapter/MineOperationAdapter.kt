package com.boco.whl.funddemo.module.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.boco.whl.funddemo.R
import com.boco.whl.funddemo.entity.MineOperationItem
import com.bumptech.glide.Glide

/**
 * @author:honglei92
 * @time:2018/7/9
 */
class MineOperationAdapter(var context: Context, var operations: List<MineOperationItem>)
    : Adapter<MineOperationAdapter.ViewHolder>() {
    lateinit var mOnItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fragment_my, p0, false))
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.mTextView.text = operations[position].mineOperationItemName
        Glide.with(context).load(operations[position].mineOperationItemImage)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.mImageView)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.operation_text)
        var mImageView: ImageView = itemView.findViewById(R.id.operation_image)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}