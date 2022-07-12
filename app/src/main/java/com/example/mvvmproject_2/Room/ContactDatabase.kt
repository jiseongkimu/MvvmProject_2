package com.example.mvvmproject_2.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* 실질적인 데이터베이스 인스턴스를 생성할 Database 클래스
* 어노테이션을 이용해 entity를 정의하고 SQLite 버전 지정
* RoomDatabase()를 상속
* */
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    // 데이터베이스 인스턴스의 싱글톤 사용
    companion object{
        private var INSTANCE: ContactDatabase? = null

        // MainActivity에서 호출하여 database 객체를 반환하는 getInstance
        // 여러 스레드가 접근하지 못하도록 synchronized로 설정
        fun getInstance(context: Context): ContactDatabase?{
            if(INSTANCE == null){
                synchronized(ContactDatabase::class){
                    // 빌더로 인스턴스 생성
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ContactDatabase::class.java, "contact")
                        // 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}