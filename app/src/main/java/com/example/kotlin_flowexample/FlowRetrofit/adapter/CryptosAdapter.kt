package com.example.kotlin_flowexample.FlowRetrofit.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlin_flowexample.FlowRetrofit.response.ResponseCoinsList
import com.example.kotlin_flowexample.FlowRetrofit.utils.Constants.animationDuration
import com.example.kotlin_flowexample.FlowRetrofit.utils.roundToTwoDecimals
import com.example.kotlin_flowexample.FlowRetrofit.utils.toDoubleToFloat
import com.example.kotlin_flowexample.R
import com.example.kotlin_flowexample.databinding.ItemCryptoBinding
import javax.inject.Inject

class CryptosAdapter @Inject constructor() : RecyclerView.Adapter<CryptosAdapter.MyViewHolder>() {

    private lateinit var binding: ItemCryptoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemCryptoBinding.inflate(inflater, parent, false)
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MyViewHolder() : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseCoinsList.ResponseCoinsListItem) {
            binding.apply {
                tvName.text = item.id
                tvPrice.text = "€${item.current_price.roundToTwoDecimals()}"
                tvSymbol.text = item.symbol.uppercase()
                imgCrypto.load(item.image){
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.ic_bitcoin)
                    error(R.drawable.ic_bitcoin)
                }

                lineChart.gradientFillColors = intArrayOf(
                    Color.parseColor("#2a9085"),
                    Color.TRANSPARENT
                )

                lineChart.animation.duration = animationDuration
                val listData = item.sparkline_in_7d.price.toDoubleToFloat()
                lineChart.animate(listData)

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }

            }
        }
    }

    private var onItemClickListener : ((ResponseCoinsList.ResponseCoinsListItem) -> Unit)? = null

    fun setOnItemClickListener(listener : (ResponseCoinsList.ResponseCoinsListItem) -> Unit){
        onItemClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseCoinsList.ResponseCoinsListItem>() {
        override fun areItemsTheSame(oldItem: ResponseCoinsList.ResponseCoinsListItem, newItem: ResponseCoinsList.ResponseCoinsListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseCoinsList.ResponseCoinsListItem, newItem: ResponseCoinsList.ResponseCoinsListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}