package com.example.fitnesstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val treningi = mutableListOf<Trening>()
        val filteredTreningi = mutableListOf<Trening>()
        lateinit var adapter: TreningAdapter

        val inputDystans = findViewById<EditText>(R.id.input_dystans)
        val inputCzas = findViewById<EditText>(R.id.input_czas)
        val inputKalorie = findViewById<EditText>(R.id.input_kalorie)
        val seekbarIntensywnosc = findViewById<SeekBar>(R.id.seekbar_intensywnosc)
        val textIntensywnosc = findViewById<TextView>(R.id.text_intensywnosc)
        val radioGrupa = findViewById<RadioGroup>(R.id.radio_aktywnosc)
        val btnDodaj = findViewById<Button>(R.id.btn_dodaj)
        val recyclerTreningi = findViewById<RecyclerView>(R.id.recycler_treningi)

        adapter = TreningAdapter(treningi)
        recyclerTreningi.layoutManager = LinearLayoutManager(this)
        recyclerTreningi.adapter = adapter

        seekbarIntensywnosc.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textIntensywnosc.text = "Intensywność: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        btnDodaj.setOnClickListener {
            val dystans = inputDystans.text.toString().toDoubleOrNull() ?: 0.0
            val czas = inputCzas.text.toString().toIntOrNull() ?: 0
            val kalorie = inputKalorie.text.toString().toIntOrNull() ?: 0
            val intensywnosc = seekbarIntensywnosc.progress

            val typ = when (radioGrupa.checkedRadioButtonId) {
                R.id.radio_spacer -> "Spacer"
                R.id.radio_bieg -> "Bieg"
                else -> "Trening siłowy"
            }

            val trening = Trening(dystans, czas, kalorie, intensywnosc, typ)
            treningi.add(trening)
            adapter.notifyDataSetChanged()

            inputDystans.text.clear()
            inputCzas.text.clear()
            inputKalorie.text.clear()
            seekbarIntensywnosc.progress = 1
            textIntensywnosc.text = "Intensywność: 1"
            radioGrupa.clearCheck()
        }
    }
}