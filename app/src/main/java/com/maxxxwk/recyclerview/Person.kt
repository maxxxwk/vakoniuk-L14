package com.maxxxwk.recyclerview

import android.util.Log

class Person(
    val name: String,
    val age: Int,
    private val mother: Person?,
    private val father: Person?
) {
    companion object {
        fun getMe(): Person {
            val greatGrandmother1 = Person("Halyna", 91, null, null)
            val greatGrandfather1 = Person("Volodymyr", 92, null, null)
            val grandmother1 = Person("Maria", 72, greatGrandmother1, greatGrandfather1)
            val greatGrandmother2 = Person("Kateryna", 90, null, null)
            val greatGrandfather2 = Person("Anton", 90, null, null)
            val grandfather1 = Person("Ivan", 72, greatGrandmother2, greatGrandfather2)
            val mother = Person("Tetiana", 50, grandmother1, grandfather1)
            val greatGrandmother3 = Person("Anna", 95, null, null)
            val greatGrandfather3 = Person("Andriy", 98, null, null)
            val grandmother2 = Person("Nadia", 75, greatGrandmother3, greatGrandfather3)
            val greatGrandmother4 = Person("Maria", 99, null, null)
            val greatGrandfather4 = Person("Petro", 98, null, null)
            val grandfather2 = Person("Dmytro", 78, greatGrandmother4, greatGrandfather4)
            val father = Person("Anatoliy", 55, grandmother2, grandfather2)
            return Person("Maksym", 18, mother, father)
        }
    }

    fun getSizeOfSubtree() = getSubtreeList().toSet().size

    private fun getSubtreeList(): MutableList<Person> {
        val list = mutableListOf(this)
        mother?.let {
            list.addAll(it.getSubtreeList())
        }
        father?.let {
            list.addAll(it.getSubtreeList())
        }
        return list
    }

    fun getRelativeByListPosition(position: Int, kinshipDegree: Int = 0): Relative? {
        if (position == 0) {
            return Relative(this, kinshipDegree)
        }
        var newPosition = position - 1
        val newKinshipDegree = kinshipDegree + 1
        var relative = mother?.let {
            it.getRelativeByListPosition(newPosition, newKinshipDegree)
        }
        if (relative == null) {
            relative = father?.let { father ->
                mother?.let { mother ->
                    newPosition -= mother.getSizeOfSubtree()
                }
                father.getRelativeByListPosition(newPosition, newKinshipDegree)
            }
        }
        return relative
    }

}