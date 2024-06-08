package com.example.convidados_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados_app.databinding.FragmentAllGuestsBinding
import com.example.convidados_app.view.adapter.GuestsAdapter
import com.example.convidados_app.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        /**
         ANOTAÇÕES DE CODIGO

         - o layoutManager, nao é o layout que vai ser mostrado as info, ele é o layout da viewGroup. Esse layoutManager, diz como os elementos
         da RecycleView vao se comportar.
         - LinearLayoutManager() ele pede um context.
         - criamos um class Adapter para ser instanciada.

         - a função do adapter é praticamente a junção da lista e do layout, ele que faz com que os 2 se conecte.
         - criamos a variavel adapter e instanciamos o GuestAdapter nela, para ...
         - a varialvel adapter, nao reprecisa ser usado o latenit nela, pois ela nao precisa, nao esta esperando ninguem, nao ta esperando
         um context nem nada. Entao podemos instaciar o GuestAdapter.

         **/

        // Layout
        binding.recycleAllGuests.layoutManager = LinearLayoutManager(context)

        // Adapter
        binding.recycleAllGuests.adapter = adapter

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        // mudamos o obeserve de .text para list, pois nao queremos observar o text e sim a lista.
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)


        }
    }
}