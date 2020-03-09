package com.juanbas.ekonomin.dataBase.ViewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository

/** Controls user data retrieved from database */
class BudgetDataViewModel(application: Application) : AndroidViewModel(application) {
    private val budgetRepository by lazy { BudgetRepository(application) }

    fun insert(budget: BudgetEntity) {
        budgetRepository.insertBudget(budget)
    }

    fun update(budget: BudgetEntity) {
        budgetRepository.updateBudget(budget)
    }

    fun delete(budget: BudgetEntity) {
        budgetRepository.deleteBudget(budget)
    }

    fun getBudgetByOwnerId(id: String) = budgetRepository.getBudgetByOwnerId(id)
    fun getAllBudgetss() = budgetRepository.getAllBudgets()
}