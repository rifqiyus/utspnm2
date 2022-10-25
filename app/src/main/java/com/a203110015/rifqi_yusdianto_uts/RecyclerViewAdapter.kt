package com.a203110015.rifqi_yusdianto_uts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a203110015.rifqi_yusdianto_uts.db.UserEntity
import kotlinx.android.synthetic.main.recyclerview_row.view.*

/*
Setiap elemen individual dalam daftar ditentukan oleh objek pemegang tampilan.
Saat pemegang tampilan dibuat, tidak ada data yang diatribusikan dengannya.
Setelah pemegang tampilan dibuat, RecyclerView akan mengikat properti tersebut ke datanya.
Anda dapat menentukan pemegang tampilan dengan memperluas RecyclerView.ViewHolder.
 */
class RecyclerViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    // inisialiasi array
    var items  = ArrayList<UserEntity>()

    // melakukan set data array
    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }
/*
ViewHolder menyediakan semua fungsi untuk item daftar Anda. Pemegang tampilan Anda adalah wrapper di sekitar View, dan tampilan tersebut dikelola oleh RecyclerView.
 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater, listener)
    }
// menggenerate nomor indeks
    override fun getItemCount(): Int {
        return items.size
    }
/*
onBindViewHolder(): RecyclerView memanggil metode ini untuk mengatribusikan ViewHolder dengan data.
Metode ini mengambil data yang sesuai dan menggunakan data tersebut untuk mengisi tata letak pemegang tampilan.
 */
    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }


//
    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {

        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val tvPhone = view.tvPhone
        val deleteUserID = view.deleteUserID

        fun bind(data: UserEntity) {
            tvName.text = data.name

            tvEmail.text = data.email


            tvPhone.text = data.phone

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }
//
    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}