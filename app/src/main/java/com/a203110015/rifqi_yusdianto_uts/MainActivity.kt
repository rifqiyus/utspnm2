package com.a203110015.rifqi_yusdianto_uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.a203110015.rifqi_yusdianto_uts.db.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
//
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // menyetel recycle view
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }
        // mengembalikan view model
            viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })


        // jika tombol disave disimpan, maka akan menyimpan data
        saveButton.setOnClickListener {
            val name  = nameInput.text.toString()
            val email  = emailInput.text.toString()
            val phone = phoneInput.text.toString()
            if(saveButton.text.equals("Save")) {
                val user = UserEntity(0, name, email, phone)
                viewModel.insertUserInfo(user)
            } else {
                val user = UserEntity(nameInput.getTag(nameInput.id).toString().toInt(), name, email, phone)
                viewModel.updateUserInfo(user)
                saveButton.setText("Save")
            }
            nameInput.setText("")
            emailInput.setText("")
        }
    }



    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    //
    override fun onItemClickListener(user: UserEntity) {
        nameInput.setText(user.name)
        emailInput.setText(user.email)
        phoneInput.setText(user.phone)
        nameInput.setTag(nameInput.id, user.id)
        saveButton.setText("Update")
    }
}