package com.moczul.diffutilsample

import android.os.Bundle
import android.support.v7.util.DiffUtil

class ActorDiffCallback(
        private val oldList: List<Actor>,
        private val newList: List<Actor>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    //判断是否是同一个对象
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    //判断是不是相同的内容
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name.equals(newList[newItemPosition].name)
    }

    /**
     * 当 areItemsTheSame = true, areContentsTheSame返回false时，触发这个方法的调用
     */
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator

        var newObj: Actor = newList.get(newItemPosition)
        var oldObj: Actor = oldList.get(oldItemPosition)

        var bundle: Bundle = Bundle();
        if (!newObj.name.equals(oldObj.name)) {
            bundle.putString("name", newObj.name)
        }

        if (bundle.isEmpty) {
            return null
        }
        return bundle
    }
}