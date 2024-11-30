package com.example.fitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TreningAdapter(private val treningi: List<Trening>) :
    RecyclerView.Adapter<TreningAdapter.TreningViewHolder>() {

    class TreningViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textOpis: TextView = view.findViewById(R.id.text_opis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreningViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trening, parent, false)
        return TreningViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreningViewHolder, position: Int) {
        val trening = treningi[position]
        holder.textOpis.text = "${trening.typ}: ${trening.dystans} km, ${trening.czas} min, ${trening.kalorie} kcal"
    }

    override fun getItemCount() = treningi.size
}