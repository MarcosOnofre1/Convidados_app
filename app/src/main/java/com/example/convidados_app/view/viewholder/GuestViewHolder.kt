package com.example.convidados_app.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados_app.R
import com.example.convidados_app.databinding.RowGuestBinding
import com.example.convidados_app.model.GuestModel
import com.example.convidados_app.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    /**
     ANOTAÇÕES DE CODIGO

     - podemos fazer dessa maneira:
     itemView.findViewById<TextView>(R.id.text_name).text = guest.name

     porem, acaba que assim, quebramos um padrao de codigo que venhamos usando no nosso projeto. Funciona dessa maneira, porem, vamos usar
     um outro metodo.

     - mudamos o GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) para
     GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root)

     Usamos o "private val bind" porq dai conseguimos usar o bind.

     *OnClickListener
     - ViewHolder nao vai ter o controle do nosso cliclo de vida da activity, talvez nem tenha o contexto tambem. Entao a implementação
     - Adicionamos um "private val listener: OnGuestListener" na classe GuestViewHolder()
     - usamos o guest.id no setOnClickListener, usando dessa forma, temos exatamente o convidado que foi cliclado por usar a ID, caso precise
     fazer uma remoção ou algo do tipo, facilita para gente, pois usamos o ID do convidado.

     *OnLongClickListener
     - usamos uma classe anonima no OnLongClickListener e implementamos os membros, depois convertemos para lambda e assim, passamos o onDelete(),
     e enseguida o true, pois retorna um Boolean.
     - Disparandon esse onDelete(), temos que responder no AlllGuestsFragments

     **/
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener(View.OnLongClickListener {
            listener.onDelete(guest.id)
            true
        })
    }

}