package com.route.gpstrackerc39_gsat.SOLID

import android.provider.ContactsContract.Data
import android.view.View
import android.widget.Button

// SOLID ->
/**
 *  'S'ingle Responsibility Principle
 *  'O'pen / Closed Principle <-> Open For Extension / Closed For Modification
 *  'L'iskov Substitution Principle
 *  'I'nterface Segregation Principle
 *  'D'ependency Inversion Principle
 *
 */
// Maintainble - Reusable - Testable

// Class or function should have single responsiplity


data class Product(
    val id: Int? = null,
    val name: String? = null,
    val price: Double? = null,
) {
    fun showProductDetails() {
        print("Id : $id")
        print("Name : $name")
        print("Price : $price")
    }


}

data class Receipt(
    val products: List<Product>? = null
) {

    fun printReceipt(products: List<Product>) {
        //
        // 15
    }
}


open class Calculator {
    fun add() {

    }

    fun sub() {

    }

    fun mul() {

    }

    fun div() {

    }
}
// sin , Cos , Tan

class ScientificCalculator : Calculator() {
    fun sin() {

    }

    fun cos() {

    }

    fun tan() {

    }

}

// 'L'iskov Substitution Principle

// class Parent    ,         class Child : Parent()

// Scientific Calculator
fun calculate(calculator: Calculator) {
    calculator.add()
}

// 'I'nterface Segregation Principle

interface OnClickListener {
    fun onClick()
}

interface OnSwipeListener {
    fun onSwipe()
}

interface OnLongClickListener {
    fun onLongClick()
}

object onClick : OnClickListener {
    override fun onClick() {
        // LOGIC
    }
}

object onSwipe : OnSwipeListener {
    override fun onSwipe() {

    }
}

val onLongClick = object : OnLongClickListener {
    override fun onLongClick() {

    }
}

//'D'ependency Inversion Principle


// RecyclerView SettingsAdapter (val settingsList : List<SettingsItem>)
//
//interface Switchable {
//    fun activate()
//    fun deactivate()
//}

//class Sqlite : OfflineDataSource {
//
//    override fun activate() {
//        // activate to lamp
//    }
//
//    override fun deactivate() {
//        // deactivate to lamp
//    }
//}

// Design Patterns

// A common software solutions for a common software problems
// // Singleton pattern
// Builder Pattern


// Creational -> the way of constructing or Creating an object
//   Singleton
//   Builder
//   Factory
//   Abstract Factory
//   Prototype

// Structural -> Adapter -> The way that classes communicate

// Behavorial -> The way of communication between objects


// singleton -> Creational

// Single Object + Global Accessible


class Database private constructor() {

    companion object {
        private var INSTANCE: Database? = null
        fun getInstance(): Database {
            if (INSTANCE == null)
                INSTANCE = Database()

            return INSTANCE!!
        }
    }

}

val database1 = Database.getInstance() // object number 1
val database2 = Database.getInstance() // object number 1
val database5 = Database.getInstance() // object number 1
fun logic() {
    // Database.getInstance().getTodosDao().getAllTasks()
    // Clean Architecture
    // UI <-> Domain <-> Data
}
// Database ->

object Data_Base {
    // database name

}

val database3 = Data_Base
val database4 = Data_Base

// Adapter
// 2 Screens ->
// Recycler View -> Adapter = new Adapter() // object Number #1
// Recycler View -> Adapter = new Adapter() // Object number #2


// Room -> Singleton Pattern + Builder Pattern
//val database1 = Database() // object number 1
//val database2 = Database() // object number 2


// builder pattern

class Car private constructor(private val model: String? = null, private val year: Int? = null) {

    class Builder(private var model: String? = null, private var year: Int? = null) {
        fun model(builderModel: String) = apply {
            this.model = builderModel
        }

        fun year(builderYear: Int) = apply {
            this.year = builderYear
        }

        fun build(): Car {
            return Car(model, year)
        }
    }
}


val car = Car.Builder()
    .model("Route Car")
    .year(2024)
    .build()


fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}

// void in java
val addLambda: (num1: Int, num2: Int) -> Int = { num1, num2 ->

    num1 + num2
}

fun onSummtionDone(num1: Int, num2: Int, onSumDone: (Int) -> Unit) {
    val result = num1 + num2
    onSumDone(result)
}

// interface OnItemClickListener{
//
// }


fun interface OnItemClickListener {
    fun onItemClick()
}


fun logic2() {
    val button: Button
//    button.setOnClickListener {
//
//   }
    onSummtionDone(80, 30) {

    }
    val result = addLambda(50, 30)
    val onItemClickLister = OnItemClickListener {

    }
}







