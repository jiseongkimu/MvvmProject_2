package com.example.mvvmproject_2.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mvvmproject_2.room.Contact
import com.example.mvvmproject_2.room.ContactDao
import com.example.mvvmproject_2.room.ContactDatabase
import java.lang.Exception

/*
* 뷰모델과 상호작용하기 위해 잘 정리된(Clean) 데이터 API를 들고 있는 클래스이다.
* 앱에 필요한 데이터, 즉 내장 데이터베이스나 외부 웹 서버 등에서 데이터를 가져온다.
* 따라서 뷰모델은 DB나 서버에 직접 접근하지 않고 리포지토리에 접근하는 것으로 앱의 데이터를 관리한다.
* */
class ContactRepository(application: Application) {

    // 앞서 정의한 Room의 요소들을 초기화
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    /* 뷰모델에서 DB에 접근을 요청할 때 수행할 함수를 만든다.
     메인 스레드에서 접근하면 크래쉬가 발생하기 때문에
     별도의 스레드에서 Room의 데이터에 접근해야 한다.*/
    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact){
        try{
            val thread = Thread(Runnable {
                contactDao.insert(contact) })
            thread.start()
        } catch (e: Exception){}
    }

    fun delete(contact: Contact){

    }
}