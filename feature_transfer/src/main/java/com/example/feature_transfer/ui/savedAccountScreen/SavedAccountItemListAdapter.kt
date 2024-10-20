package com.example.feature_transfer.ui.savedAccountScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.SavedAccountGetDataCore
import com.example.feature_transfer.R

class SavedAccountItemListAdapter(
    private val savedAccounts: List<SavedAccountGetDataCore?>
) : RecyclerView.Adapter<SavedAccountItemListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedAccountItemListAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_saved_account, parent, false)
        )

    override fun getItemCount(): Int = savedAccounts.size

    override fun onBindViewHolder(holder: SavedAccountItemListAdapter.ViewHolder, position: Int) {
        holder.bind(savedAccounts[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val accountName: TextView = itemView.findViewById(R.id.tv_nama_pengguna)
        private val bankName: TextView = itemView.findViewById(R.id.tv_bank_pengguna)
        private val accountNo: TextView = itemView.findViewById(R.id.tv_rekening_pengguna)

        fun bind(savedAccounts: SavedAccountGetDataCore?) {
            accountName.text = savedAccounts?.name
            bankName.text = savedAccounts?.destinationBank
            accountNo.text = savedAccounts?.accountNumber

            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                    SavedAccountFragmentDirections.actionSavedAccountFragmentToTransferInputFragment(
                        accountDestinationName = savedAccounts?.name.toString(),
                        accountDestinationNo = savedAccounts?.accountNumber.toString(),
                        accountDestinationBank = savedAccounts?.destinationBank.toString()
                    )
                )
            }
        }
    }
}