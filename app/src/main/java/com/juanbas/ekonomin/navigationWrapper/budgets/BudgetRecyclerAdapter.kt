package com.juanbas.ekonomin.navigationWrapper.budgets


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.repositories.BudgetRepository
import kotlinx.android.synthetic.main.budget_recycler_item_row.view.*


/** Adapter used to populate the Budget instances from the database into view holders. */
class BudgetRecyclerAdapter(
    val productModel: BudgeListViewModel
) : RecyclerView.Adapter<BudgetRecyclerAdapter.BudgetHolder>() {

    private var budgets: ArrayList<BudgetEntity> = ArrayList()

    /** Assigns each Budget entity in the [budgets] list into a view holder */
    override fun onBindViewHolder(holder: BudgetHolder, position: Int) {
        holder.budgetEntity = budgets.get(position)
        holder.v.setOnClickListener {
            val intent = Intent(it.context, BudgetView::class.java)
            BudgetRepository.budgetEntity=budgets.get(position)
            startActivity(it.context,intent,null)
        }

    }

    /** Returns size from budgets list */
    override fun getItemCount() = budgets.size

    /** Inflate a layout instance for each holder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetHolder =
        BudgetHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.budget_recycler_item_row, parent, false)
        )

    /** Used to load items passed by the LiveData observer */
    fun loadItems(newItems: ArrayList<BudgetEntity>) {
        budgets = newItems
        this.notifyDataSetChanged()
    }

    /** TODO: Implement, not done yet */
    fun removeAt(position: Int) {
        val productToDelete = budgets.get(position)
        productModel.deleteBudget(productToDelete)
        budgets.removeAt(position)
        notifyItemRemoved(position)
    }

    /** Defines the view holder object and what to do with it. */
    class BudgetHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var budgetEntity: BudgetEntity? = null
            set(value) {
                field = value
                var date = "${value?.dueYear}/${value?.dueMonth}"
                v.referenceDate.text = date
            }
    }

}
