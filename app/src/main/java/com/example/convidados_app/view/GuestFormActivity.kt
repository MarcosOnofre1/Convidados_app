package com.example.convidados_app.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados_app.GuestModel
import com.example.convidados_app.viewmodel.GuestFormViewModel
import com.example.convidados_app.R
import com.example.convidados_app.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding

    private lateinit var viewmodel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()


    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){

            //view.save(GuestModel(10, "xxx", false))

        }
    }
}