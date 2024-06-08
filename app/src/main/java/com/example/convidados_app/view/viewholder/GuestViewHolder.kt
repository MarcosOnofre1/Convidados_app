package com.example.convidados_app.view.viewholder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados_app.R
import com.example.convidados_app.databinding.RowGuestBinding
import com.example.convidados_app.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    /**
     ANOTAÇÕES DE CODIGO

     - podemos fazer dessa maneira:
     itemView.findViewById<TextView>(R.id.text_name).text = guest.name

     porem, acaba que assim, quebramos um padrao de codigo que venhamos usando no nosso projeto. Funciona dessa maneira, porem, vamos usar
     um outro metodo.

     - mudamos o GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) para
     GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root)

     Usamos o private val bind porq dai conseguimos usar o bind.

     **/
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name
    }

}