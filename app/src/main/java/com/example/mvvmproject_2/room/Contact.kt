package com.example.mvvmproject_2.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Database 안에 있는 테이블을 Java나 Kotlin 클래스로 나타낸 것(데이터 모델 클래스)
* */

@Entity(tableName = "contact") // 필수 어노테이션
data class Contact(

    // 하나 이상의 기본키를 설정해야 한다.
    // null인 경우 자동으로 생성되도록 autoGenerate = ture
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    // 칼럼명을 지정할 수 있지만 변수명과 같이 쓰려면 생략 가능
    // 그렇기 때문에 (tableName = "contact") 부분도 생략할 수 있다.
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char
) {
    constructor() : this(null, "", "", '\u0000')
}

