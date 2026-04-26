package com.sraccelerator.easyorder.data.local

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

object RoomQueryBuilder {
    fun build(tableName: String, criteria: Array<out QueryCriteria>): SupportSQLiteQuery {
        val query = StringBuilder("SELECT * FROM $tableName")
        val args = mutableListOf<Any>()

        if (criteria.isNotEmpty()) {
            query.append(" WHERE ")
            criteria.forEachIndexed { index, criterion ->
                if (index > 0) query.append(" AND ")
                when (criterion) {
                    is QueryCriteria.Equals -> {
                        query.append("${criterion.field} = ?")
                        args.add(criterion.value)
                    }
                    is QueryCriteria.GreaterThan -> {
                        query.append("${criterion.field} > ?")
                        args.add(criterion.value)
                    }
                    is QueryCriteria.InList -> {
                        val placeholders = criterion.values.joinToString(",") { "?" }
                        query.append("${criterion.field} IN ($placeholders)")
                        args.addAll(criterion.values)
                    }
                }
            }
        }
        return SimpleSQLiteQuery(query.toString(), args.toTypedArray())
    }
}
