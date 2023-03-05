package com.juhasz.country.data

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM country WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Country>

    @Insert
    fun insertAll(vararg countries: Country)

    @Delete
    fun delete(country: Country)

    @Query("SELECT * FROM Country WHERE uid = :uid")
    fun get(uid: Int) : Country

    @Update
    fun update(country: Country)

}