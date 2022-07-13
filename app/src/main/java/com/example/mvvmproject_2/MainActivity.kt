package com.example.mvvmproject_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject_2.adapter.ContactAdapter
import com.example.mvvmproject_2.room.Contact
import com.example.mvvmproject_2.viewModel.ContactViewModel
import com.example.mvvmproject_2.databinding.ItemContactBinding

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var itemContactBinding: ItemContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adpater = ContactAdapter({ contact ->
            //
        }, { contact ->
            deleteDialog(contact)
        })


        /*
        * ContactViewModel 인스턴스를 만들고 이를 관찰하는 역할을 수행
        * 뷰모델 객체는 직접적으로 초기화 해주는 것이 아니라, 안드로이드 시스템을 통해 생성해준다.
        * 만약 이미 생성된 ViewModel 인스턴스가 있다면 시스템에서는 이를 반환할 것이므로 메모리 낭비를 줄여준다.
        * 따라서 ViewModelProvider를 이용해 get해준다
        * */

        // 원래 ViewModelProviders.of(this)였으나 depricated
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        /*
         * Observe를 만들어서 뷰모델이 어느 액티비티/프래그먼트의 생명주기를 관찰할 것인지 정한다.
         * 이 익티비티가 파괴되는 시점에 시스템에서 뷰모델도 자동으로 파괴할 것이다.
         * Observe는 onChanged 메소드를 통해 관찰하고 있던 LiveData가 변하면 무엇을 할 것인지 액션을 정할 수 있다.
         * 이후 액티비티/프래그먼트가 활성화되어 있다면 View에서 LiveData를 관찰하여 자동으로 변경 사항을 파악하고 수행한다.
         */

        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.set
        })
    }

    private fun deleteDialog(contact: Contact){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("No"){ _, _ ->}
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
}