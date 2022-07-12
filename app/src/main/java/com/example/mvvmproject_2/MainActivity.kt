package com.example.mvvmproject_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproject_2.Room.Contact
import com.example.mvvmproject_2.ViewModel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ContactViewModel 인스턴스를 만들고 이를 관찰하는 역할을 수행
        contactViewModel = ViewModelProvider.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            // update UI
        })
    }
}