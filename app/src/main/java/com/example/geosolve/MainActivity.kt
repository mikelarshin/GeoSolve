package com.example.geosolve

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    companion object {
        var presenter: Presenter = Presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }
}
