package com.example.shoplistapp

import android.app.Application
import androidx.room.Room
import com.example.shoplistapp.database.ProductDatabase
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.repository.ProductRepository
import com.example.shoplistapp.repository.ShopListRepository
import com.example.shoplistapp.vm.ArchivesViewModel
import com.example.shoplistapp.vm.ShopListViewModel
import com.example.shoplistapp.vm.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    private val viewModelModule = module {
        viewModel { ProductViewModel(get(), get()) }
        viewModel { ShopListViewModel(get()) }
        viewModel { ArchivesViewModel(get()) }
    }

    private val databaseModule = module {
        single { provideProductDatabase() }
        single { ProductRepository(get()) }
        single { provideShopListDatabase() }
        single { ShopListRepository(get()) }
    }

    private fun provideProductDatabase() =
        Room.databaseBuilder(applicationContext, ProductDatabase::class.java, "item.db")
            .fallbackToDestructiveMigration()
            .build()

    private fun provideShopListDatabase() =
        Room.databaseBuilder(applicationContext, ShopListDatabase::class.java, "all_lists.db")
            .fallbackToDestructiveMigration()
            .build()

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(viewModelModule, databaseModule)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(moduleList)
        }
    }
}