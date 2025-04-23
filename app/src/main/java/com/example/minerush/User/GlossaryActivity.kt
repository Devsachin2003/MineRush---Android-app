package com.example.minerush.User

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.GlossaryAdaptor
import com.example.minerush.DataClass.GlossaryData
import com.example.minerush.databinding.ActivityGlossaryBinding

class GlossaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlossaryBinding
    private var glossaryterms = ArrayList<GlossaryData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirects()
        setData()
        setAdapters()
        setListeners()

    }

    private fun setListeners() {
        binding.backIV.setOnClickListener{
            finish()
        }
    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
    private fun setData() {
        glossaryterms.add(GlossaryData("DGMS", "DGMS (Directorate General of Mines Safety) is an Indian government agency under the Ministry of Labour and Employment. It is responsible for ensuring the safety, health, and welfare of workers in mines and oil fields across India. DGMS formulates safety regulations, inspects mining operations, investigates accidents, and promotes safe practices in the mining industry."))
        glossaryterms.add(GlossaryData("Explosives", "Explosives are highly reactive materials that release a significant amount of energy in a short period when triggered. They are used in various industries for purposes like mining, construction, and military applications due to their ability to break rock, demolish structures, and propel projectiles. Proper handling and safety protocols are essential to prevent accidents and ensure their effective use."))
        glossaryterms.add(GlossaryData("PER (Permit to Mine)", "A permit to mine is official approval from regulatory bodies allowing companies to begin mining in designated areas. It mandates adherence to safety, environmental, and operational guidelines to ensure responsible mining practices."))
        glossaryterms.add(GlossaryData("Colliery", "A coal mine and the buildings or equipment used in connection with it."))
        glossaryterms.add(GlossaryData("COI Proceedings", "COI proceedings refer to the processes involved in a Court of Inquiry (COI), which is a formal investigative procedure used primarily within military organizations to investigate incidents, accidents, or allegations of misconduct. The purpose of a COI is to establish facts, determine accountability, and recommend corrective actions if necessary. The proceedings typically involve the collection of evidence, witness testimonies, and a thorough examination of the circumstances surrounding the incident under investigation. The findings of a COI are documented in a report, which may lead to further disciplinary actions or policy changes."))
        glossaryterms.add(GlossaryData("Blasting Plan", "A blasting plan is a detailed strategy used in mining, construction, and demolition to ensure the safe and efficient use of explosives. It outlines the objectives, methodologies, types and quantities of explosives to be used, blast design, timing sequences, safety measures, and environmental considerations. The plan aims to achieve the desired fragmentation while minimizing risks to personnel, equipment, and surrounding areas"))
        glossaryterms.add(GlossaryData("ANFO", "ANFO, or Ammonium Nitrate Fuel Oil, is a widely used industrial explosive composed of ammonium nitrate and fuel oil. It is known for its effectiveness in mining, quarrying, and construction due to its high detonation velocity and relative safety in handling. ANFO is popular because it is cost-effective, stable under normal conditions, and easy to manufacture and use."))

    }

    private fun setAdapters() {
        binding.GlossaryRV.layoutManager = LinearLayoutManager(this)
        val glossaryDisplayAdapter = GlossaryAdaptor(glossaryterms)
        binding.GlossaryRV.adapter = glossaryDisplayAdapter

    }


}