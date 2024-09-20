package com.example.feature_mutasi.ui.mutasiScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.response.MutationDataCore
import com.example.feature_mutasi.R

class MutationPerDateAdapter(
    private val groupedMutationList: Map<String, List<MutationDataCore>>,
    private val accountNumber: String
) : RecyclerView.Adapter<MutationPerDateAdapter.ViewHolder>() {
    private val dates = groupedMutationList.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_per_date, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        holder.bind(date, groupedMutationList[date] ?: emptyList())
    }

    override fun getItemCount(): Int = groupedMutationList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        private val rvTransaksi: RecyclerView = itemView.findViewById(R.id.rv_per_transaction)

        fun bind(date: String, mutations: List<MutationDataCore>) {
            tvTanggal.text = date
            val transferAdapter = TransactionItemListAdapter(mutations, accountNumber)
            rvTransaksi.adapter = transferAdapter
            rvTransaksi.layoutManager = LinearLayoutManager(rvTransaksi.context)
        }
    }
}