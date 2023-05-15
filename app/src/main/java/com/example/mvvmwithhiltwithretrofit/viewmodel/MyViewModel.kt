package com.example.mvvmwithhiltwithretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithhiltwithretrofit.Repository.Repository
import com.example.mvvmwithhiltwithretrofit.favorite_item.ProductFav
import com.example.mvvmwithhiltwithretrofit.netWork.dataClass.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel(){


    //for api
    lateinit var liveData: MutableLiveData<List<Product>>

    init {
        liveData= MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<List<Product>>{
        return liveData
    }

    fun loadDataFromApi(){
        repository.makeApiCall(liveData)
    }
    //for api


    //for local database
    private var userData: MutableLiveData<List<ProductFav>> = MutableLiveData()

    init {
        loadRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<ProductFav>> {
        return userData
    }

    fun loadRecords(){
        val list = repository.getRecords()

        userData.postValue(list)
    }

    fun insertRecord(userEntity: ProductFav) {
        repository.insertRecord(userEntity)
        loadRecords()
    }

    fun deleteRecord(productFav: ProductFav) {
        repository.deleteRecordById(productFav)
        loadRecords()
    }

    fun getFavorites(): List<ProductFav> {
        return repository.getRecords()
    }


}
