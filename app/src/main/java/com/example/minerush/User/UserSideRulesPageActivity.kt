package com.example.minerush.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityUsersideRulesPageBinding

class UserSideRulesPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersideRulesPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUsersideRulesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            backIV.setOnClickListener { finish() }

            val ruleMap = mapOf(
                cmaCL to "DisplayCMA1952",
                indianExplosiveActCL to "DisplayIEA1884",
                ccoCL to "DisplayCCO2000",
                ccoaCL to "DisplayCCO2004",
                landAcquisitionActCL to "DisplayLandacquisition1894",
                cmrCL to "DisplayCMR2017",
                paymentOfWagesCL to "DisplayPaymentofwages1956",
                cbaCL to "DisplayCBAact1957",
                randActCL to "DisplayRandR"
            )

            for ((view, ruleId) in ruleMap) {
                view.setOnClickListener {
                    Log.d("UserSideRulesPage", "Navigating with Rule ID: $ruleId")
                    val intent = Intent(this@UserSideRulesPageActivity, EachRulePageActivity::class.java)
                    intent.putExtra("id", ruleId)
                    startActivity(intent)
                }
            }
        }
    }
}
