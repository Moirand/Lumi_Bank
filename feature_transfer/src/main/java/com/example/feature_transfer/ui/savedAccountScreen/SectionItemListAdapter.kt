package com.example.feature_transfer.ui.savedAccountScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.response.SavedAccountGetDataCore
import com.example.feature_transfer.R

class SectionItemListAdapter(
    private val savedAccounts: List<SavedAccountGetDataCore?>,
) : RecyclerView.Adapter<SectionItemListAdapter.ViewHolder>() {
    private var filteredSavedAccounts = savedAccounts
    private val sections = setSectionLetters(filteredSavedAccounts)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_section, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section, savedAccounts)
    }

    override fun getItemCount(): Int = filteredSavedAccounts.size

    fun search(query: String?) {
        filteredSavedAccounts = savedAccounts.filter { savedAccounts ->
            savedAccounts?.name?.contains(query.orEmpty(), ignoreCase = true) == true
                    || savedAccounts?.accountNumber?.contains(
                query.orEmpty(),
                ignoreCase = true
            ) == true
        }
    }

    private fun filteredBySection(
        section: String,
        savedAccounts: List<SavedAccountGetDataCore?>
    ): List<SavedAccountGetDataCore?> = savedAccounts.filter {
        it?.name?.startsWith(section, ignoreCase = true) == true
    }

    private fun setSectionLetters(accounts: List<SavedAccountGetDataCore?>?): MutableList<String> {
        val sections = mutableListOf<String>()
        accounts?.let {
            for (account in accounts) {
                val firstLetter = account?.name?.first()!!.uppercase()
                if (!sections.contains(firstLetter)) {
                    sections.add(firstLetter)
                }
            }
        }
        return sections
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSection: TextView = itemView.findViewById(R.id.tv_section)
//        private val rvSavedAccount: RecyclerView = itemView.findViewById(R.id.rv_akun_tersimpan)

        fun bind(section: String, savedAccounts: List<SavedAccountGetDataCore?>) {
            Log.d("Andre", savedAccounts.toString())
            tvSection.text = section

//            with(rvSavedAccount) {
//                adapter = SavedAccountItemListAdapter(filteredBySection(section, savedAccounts))
//                layoutManager = LinearLayoutManager(rvSavedAccount.context)
//            }
        }
    }
}