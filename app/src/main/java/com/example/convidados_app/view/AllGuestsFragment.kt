package com.example.convidados_app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados_app.constants.DataBaseConstants
import com.example.convidados_app.databinding.FragmentAllGuestsBinding
import com.example.convidados_app.view.adapter.GuestsAdapter
import com.example.convidados_app.view.listener.OnGuestListener
import com.example.convidados_app.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
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

         *Intent
         - usamos o "bundle" no intent.bundle
         - chamamos o .putInt() por receber oque nos queremos, um id (id do onclick), que tbm funciona no formato, chave-valor
         - esse bundle é o pacote, pegamos o pacote e quando for starta a activity, starte com essas informações a mais.
         - mudamos o "guestid" para DataBaseConstants.GUEST.ID, assim, evitamos erros de digtação.

         **/

        // Layout
        binding.recycleGuests.layoutManager = LinearLayoutManager(context)

        // Adapter
        binding.recycleGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent =  Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }
        adapter.attachListener(listener)
        observe()

        return binding.root
    }

    // colocamos o getAll() no onResume que assim que o usuario clicar no convidado pra editar, ele atualiza ja em tempo real.
    override fun onResume() {
        super.onResume()
        viewModel.getAll()

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