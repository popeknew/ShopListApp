package com.example.shoplistapp.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.model.ShopList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopListRepositoryTest {

    lateinit var shopListDb: ShopListDatabase
    lateinit var repository: ShopListRepository

    private val testShopList = ShopList(name = "testShopListName", archived = false)

    @Before
    fun setUpDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        shopListDb = Room.inMemoryDatabaseBuilder(context, ShopListDatabase::class.java).build()
        repository = ShopListRepository(shopListDb)
    }

    @After
    fun closeDatabase() {
        shopListDb.close()
    }

    @Test
    fun addingNewListTest() {
        runBlocking {  repository.addList(testShopList) }

        GlobalScope.launch(Dispatchers.IO) {
            repository.allShopLists.value?.let { list ->
                assertTrue(list.contains(testShopList))
            }
        }
    }

    @Test
    fun removeExistedListTest() {
        runBlocking { repository.addList(testShopList) }

        GlobalScope.launch(Dispatchers.IO) {
            repository.allShopLists.value?.let { list ->
                assertTrue(list.contains(testShopList))
            }
        }

        runBlocking { repository.removeList(testShopList) }

        GlobalScope.launch(Dispatchers.IO) {
            repository.allShopLists.value?.let {  list ->
                assertFalse(list.contains(testShopList))
            }
        }
    }

    @Test
    fun updateExistingListTest() {
        runBlocking { repository.addList(testShopList) }
        testShopList.archived = true
        runBlocking { repository.updateShopList(testShopList) }

        GlobalScope.launch(Dispatchers.IO) {
            repository.allShopLists.value?.first { it.uid == testShopList.uid }?.let { shopList ->
                assertTrue(shopList.archived)
            }
        }
    }
}