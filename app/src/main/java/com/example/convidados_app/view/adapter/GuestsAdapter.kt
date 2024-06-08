package com.example.convidados_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados_app.databinding.RowGuestBinding
import com.example.convidados_app.model.GuestModel
import com.example.convidados_app.view.viewholder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    /**
     ANOTAÇÕES DE CODIGO

     - onCreateViewHolder: ele faz a criação do layout, pra cada item do Oncreate, ele é chamado.
     - onBindViewHolder: ele faz a "cola", depois de criado, ele faz a cola e atribui os valores para o layout
     - getItemCount: diz o tamanho da lista. Ela precisa saber o tamanho da lista para alocar o espaço necessario na memoria.
     - ViewHolder: ele que tem as referencia dos elementos de interface. Pega o itemView (image, buttom, etc.) e pode atribuir valores.

     - criamos o variavel guestList que vai ser a listagem de convidados que vamos atribuir o valor da list.
     - ainda na variavel guestList, passamos um valor, o listOf(), passamos ela vazia

     * onCreateViewHolder
     - Inflamos o layout row_guest.xml, usamos o inflate nela, mas pra usar o inflate, vamos usar o viewBinding (a viewBinding é criada de acordo com layout,
     por isso o RowGuestViewBinding)
     - RowGuestBinding, ela espera no inflate um LayoutInflater, mas como ela nao nem uma activity e nem uma fragment, é uma RecyclerView, ou seja, nao temos
     o layoutInflater facil assim, vamos ter que pegar ele. So que temos o parent do onCreateViewHolder, que permite pegar tanto o layoutInflater quanto o context
     porq é que ta chamando nossa adapter.
     - o attachToParent ele cria e associa na RecycleView, e nao queremos controlar isso, entao usamos o "false" p/ o GuestAdapter controlar.
     - passamos o item.root porq so o item nao é o suficiente.

     * onBindViewHolder
     - o .bind vai fazer a ligação entre os elementos de interface com os dados, ou seja, vai fazer um bind dos meus dados.
     - o bind nao vai fazer a ligação da lista inteira, é somente uma posição (position), ou seja, vai fazer o bind dessa posição

     - Se por acaso tivemos um cenario, onde a RecycleView é criada e depois a lista é recebida, pode ser que a ReciclerView nao saiba que ela tem que se atualizar, nesse
     caso usamos o notifyDataSetChanged() que a sua função é notificar a ReciclerView faz com que ela se atualize, se crie denovo.
     **/

    private var guestList: List<GuestModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()

    }
}