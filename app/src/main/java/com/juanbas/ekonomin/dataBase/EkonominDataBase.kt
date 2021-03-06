package com.juanbas.ekonomin.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juanbas.ekonomin.dataBase.daos.BudgetDao
import com.juanbas.ekonomin.dataBase.daos.ExpenseDao
import com.juanbas.ekonomin.dataBase.daos.IncomeDao
import com.juanbas.ekonomin.dataBase.daos.UserDao
import com.juanbas.ekonomin.dataBase.entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.entities.ExpenseEntity
import com.juanbas.ekonomin.dataBase.entities.IncomeEntity
import com.juanbas.ekonomin.dataBase.entities.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Database creation class. */
@Database(entities = arrayOf(BudgetEntity::class, IncomeEntity::class, UserEntity::class, ExpenseEntity::class), version = 9)
abstract class EkonominDataBase : RoomDatabase() {

    abstract fun budgetDao(): BudgetDao
    abstract fun incomeDao(): IncomeDao
    abstract fun userDao(): UserDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        private var INSTANCE: EkonominDataBase? = null

        fun getInstance(context: Context): EkonominDataBase? {
            if (INSTANCE == null) {
                synchronized(EkonominDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        EkonominDataBase::class.java, "ekonomin_seller.db"
                    )
                        .addCallback(roomCallback)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                if (db != null) {
                    super.onCreate(db)
                }
                populateCategories(INSTANCE)
            }
        }

        fun populateCategories(dataBase: EkonominDataBase?): Job {
            return GlobalScope.launch {
                //dataBase?.categoryDao()?.insertCategory(UserEntity(1,"unspecified"))
            }
        }
    }

}