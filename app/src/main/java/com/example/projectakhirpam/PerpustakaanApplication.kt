package com.example.projectakhirpam

import android.app.Application
import com.example.projectakhirpam.repository.AppContainer
import com.example.projectakhirpam.repository.PerpustakaanContainer

class PerpustakaanApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = PerpustakaanContainer()
    }
}