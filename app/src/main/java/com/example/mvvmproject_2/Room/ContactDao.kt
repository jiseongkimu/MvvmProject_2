package com.example.mvvmproject_2.Room

import androidx.lifecycle.LiveData
import androidx.room.*

/*
* 테이블인 Entity Contact에 접근해 질의(쿼리)를 수행하는 인터페이스
* */
@Dao
interface ContactDao {

    // LivaData를 반환해준다.
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    // Insert와 Update에서는 onConflict 속성을 지정하여 중복된 데이터의 처리를 결정할 수 있다.
    // OnConflictStrategy 인터페이스를 호출해 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK
    // 등의 액션이 지정 가능하다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}