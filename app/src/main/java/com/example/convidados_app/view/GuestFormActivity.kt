package com.example.convidados_app.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados_app.viewmodel.GuestFormViewModel
import com.example.convidados_app.R
import com.example.convidados_app.constants.DataBaseConstants
import com.example.convidados_app.databinding.ActivityGuestFormBinding
import com.example.convidados_app.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewmodel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        supportActionBar?.hide()

        observe()

        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)
            viewmodel.save(model)

            finish()

        }
    }

    private fun observe() {
        viewmodel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAusente.isChecked = true
            }
        })

    }

    /**
    ANOTAÇÃO DE CODIGO

     *BUNDLE
    - para se previnir que nosso bundle seja nulo, pois se dermos um get no bundle, a nossa intent da MainActivity nao tem valor nenhum
    entao, para nao dar um nullpoint, fazemos um if(!= null)
    - na fragment, usamos um putInt, aqu vamos usar o getInt
    - com o get() criado na GuestFormViewModel e trazendo para ca, fazemos com que essa função loadData(), que faz a logica de verificar
    se tem dados ou nao. Se a activity foi inicializada com informações para edição ou nao.
     **/

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewmodel.get(guestId)
        }
    }
}