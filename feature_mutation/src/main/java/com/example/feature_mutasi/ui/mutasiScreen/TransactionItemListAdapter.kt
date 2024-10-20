package com.example.feature_mutasi.ui.mutasiScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.common.toRupiah
import com.example.core.model.response.MutationDataCore
import com.example.feature_mutasi.R

class TransactionItemListAdapter(
    private val mutationList: List<MutationDataCore>,
    private val accountNumber: String
) : RecyclerView.Adapter<TransactionItemListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_transaction, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mutationList[position])

    override fun getItemCount(): Int = mutationList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icTransaksi: ImageView = itemView.findViewById(R.id.ic_transaksi)
        private val tvJenisTransaksi: TextView = itemView.findViewById(R.id.tv_tipe_transaksi)
        private val tvKeteranganTransaksi: TextView =
            itemView.findViewById(R.id.tv_keterangan_transaksi)
        private val tvNominalIdr: TextView = itemView.findViewById(R.id.tv_nominal_idr)

        @SuppressLint("SetTextI18n")
        fun bind(mutation: MutationDataCore) {
            when (mutation.type) {
                "transfer" -> {
                    icTransaksi.setImageResource(com.example.common.R.drawable.ic_transfer)
                    tvJenisTransaksi.text = "Transfer"
                    tvKeteranganTransaksi.text = "Transfer ke ${mutation.accountTo}"
                }

                "top_up" -> {
                    icTransaksi.setImageResource(com.example.common.R.drawable.ic_topup)
                    tvJenisTransaksi.text = "Top Up"
                    tvKeteranganTransaksi.text = "Top Up ke ${mutation.accountTo}"
                }
            }
            if (mutation.accountFrom == accountNumber) {
                tvNominalIdr.text = "- ${mutation.amount.toString().toRupiah()}"
                tvNominalIdr.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.common.R.color.secondary_red
                    )
                )
            } else {
                tvNominalIdr.text = "+ ${mutation.amount.toString().toRupiah()}"
                tvNominalIdr.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.common.R.color.secondary_green
                    )
                )
            }
        }
    }
}